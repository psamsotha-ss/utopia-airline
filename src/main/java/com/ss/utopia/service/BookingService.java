package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ss.utopia.db.DatabaseException;
import com.ss.utopia.db.DatabaseManager;
import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Passenger;
import com.ss.utopia.domain.User;
import com.ss.utopia.repository.BookingRepository;
import com.ss.utopia.repository.BookingUserRepository;
import com.ss.utopia.repository.FlightBookingRepository;
import com.ss.utopia.repository.PassengerRepository;

public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    private BookingUserRepository bookingUserRepository;
    private BookingRepository bookingRepository;
    private FlightBookingRepository flightBookingRepository;
    private PassengerRepository passengerRepository;
    private Connection connection;

    public BookingService(BookingUserRepository bookingUserRepository,
                          BookingRepository bookingRepository,
                          FlightBookingRepository flightBookingRepository,
                          PassengerRepository passengerRepository,
                          Connection connection) {
        this.bookingUserRepository = bookingUserRepository;
        this.bookingRepository = bookingRepository;
        this.flightBookingRepository = flightBookingRepository;
        this.passengerRepository = passengerRepository;
        this.connection = connection;
    }

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Create a new booking.
     * @param traveler the user to book the flight for
     * @param flight the flight to book
     * @param dob the date of birth of the user
     * @param gender the gender of the user
     * @param address the address of the user
     * @return the new {@code Booking} object
     * @throws PersistenceException if a persistence error occurs while creating the booking
     */
    public Booking createBooking(User traveler, Flight flight, String dob, String gender, String address)
            throws PersistenceException {
        return runInTransaction(() -> {
            String code = UUID.randomUUID().toString();
            Integer bookingId = bookingRepository.createBooking(Boolean.TRUE, code);
            Booking booking = new Booking(bookingId, Boolean.TRUE, code);

            flightBookingRepository.createFlightBooking(flight.getId(), bookingId);
            bookingUserRepository.createBookingUser(bookingId, traveler.getId());
            passengerRepository.createPassenger(bookingId, traveler.getGivenName(),
                    traveler.getFamilyName(), dob, gender, address);
            return booking;
        });
    }

    /**
     * Get all bookings for a user.
     * @param user the user
     * @return the bookings for the user
     * @throws PersistenceException if a database error occurs
     */
    public List<Booking> getBookingsByUser(User user) throws PersistenceException {
        try {
            return bookingRepository.findBookingsByUser(user.getId());
        } catch (SQLException ex) {
            logger.error("Could not get bookings: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Set the booking status to active or cancelled
     * @param booking the booking to set the status for
     * @param isActive true if the booking should be set to active, or false if the booking should be cancelled
     * @throws PersistenceException if a database problem occurs while setting the status
     */
    public void setBookingStatus(Booking booking, Boolean isActive) throws PersistenceException {
        runInTransaction(() -> {
            bookingRepository.updateBookingStatus(booking.getId(), isActive);
            Passenger passenger = passengerRepository.getPassengerByBookingId(booking.getId());
            if (passenger != null) {
                passengerRepository.deletePassenger(passenger.getId());
            }
            return null;
        });
    }

    interface PersistenceOperation<T> {
        T run() throws SQLException;
    }

    private Booking runInTransaction(PersistenceOperation<Booking> op) throws PersistenceException {
        try {
            connection.setAutoCommit(Boolean.FALSE);
            Booking booking = op.run();
            connection.commit();
            return booking;
        } catch (SQLException ex) {
            logger.error("Could not complete operation: {}", ex.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex2) {
                logger.error("Could not rollback transaction: {}", ex2.getMessage());
            }
            throw new PersistenceException("Could not complete operation", ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex3) {
                    logger.error("Could not close connection: {}", ex3.getMessage());
                }
            }
        }
    }

    /**
     * Get a new instance user for getting booking details
     * @return the booking service
     */
    public static BookingService newBasicInstance() {
        return new BookingService(new BookingRepository());
    }

    /**
     * Get new instance of {@code BookingService} used for transactional operations
     * @return the new booking service
     */
    public static BookingService newInstance() {
        Connection connection;
        try {
            connection = DatabaseManager.getInstance().getDataSource().getConnection();
        } catch (SQLException ex) {
            logger.error("Could not create connection: {}", ex.getMessage());
            throw new DatabaseException("Could not crete connection", ex);
        }
        return new BookingService(new BookingUserRepository(connection), new BookingRepository(connection),
                new FlightBookingRepository(connection), new PassengerRepository(connection), connection);
    }
}
