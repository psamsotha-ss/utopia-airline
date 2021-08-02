package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ss.utopia.db.DatabaseException;
import com.ss.utopia.db.DatabaseManager;
import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.User;
import com.ss.utopia.repository.BookingRepository;
import com.ss.utopia.repository.BookingUserRepository;
import com.ss.utopia.repository.FlightBookingRepository;
import com.ss.utopia.repository.PassengerRepository;

public class BookingService {

    private static final Logger logger = LogManager.getLogger(BookingService.class);

    private final BookingUserRepository bookingUserRepository;
    private final BookingRepository bookingRepository;
    private final FlightBookingRepository flightBookingRepository;
    private final PassengerRepository passengerRepository;
    private final Connection connection;

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

    interface PersistenceOperation<T> {
        T run() throws SQLException;
    }

    private Booking runInTransaction(PersistenceOperation<Booking> op) throws PersistenceException {
        Booking booking;
        try {
            connection.setAutoCommit(Boolean.FALSE);
            booking = op.run();
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
