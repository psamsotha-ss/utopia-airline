package com.ss.utopia.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.FlightBooking;
import com.ss.utopia.domain.Route;

import static com.ss.utopia.util.Converters.dateTimeFromString;

public class BookingRepository extends AbstractBaseRepository<Booking> {

    public BookingRepository() {}

    public BookingRepository(Connection connection) {
        super(connection);
    }

    public Integer createBooking(Boolean isActive, String confirmationCode) throws SQLException {
        final String sql = "INSERT INTO booking (is_active, confirmation_code) VALUES (?, ?)";
        return saveReturnPk(sql, new Object[] { isActive, confirmationCode });
    }

    public List<Booking> findBookingsByUser(Integer userId) throws SQLException {
        final String sql = "SELECT u.id AS user_id, " +
                "       b.id AS booking_id, " +
                "       b.is_active, " +
                "       f.id AS flight_id, " +
                "       f.route_id, " +
                "       f.departure_time, " +
                "       o.iata_id AS origin_id, " +
                "       o.city AS origin_city, " +
                "       d.iata_id AS destination_id, " +
                "       d.city AS destination_city " +
                "FROM user u " +
                "JOIN booking_user bu ON bu.user_id = u.id " +
                "JOIN booking b ON bu.booking_id = b.id " +
                "JOIN flight_bookings fb ON fb.booking_id = b.id " +
                "JOIN flight f ON fb.flight_id = f.id " +
                "JOIN route r ON f.route_id = r.id " +
                "JOIN airport o ON r.origin_id = o.iata_id " +
                "JOIN airport d ON r.destination_id = d.iata_id " +
                "WHERE u.id = ?";
        return find(sql, new Object[] { userId });
    }

    public void updateBookingStatus(Integer bookingId, Boolean isActive) throws SQLException {
        final String sql = "UPDATE booking SET is_active = ? WHERE id = ?";
        save(sql, new Object[] { isActive, bookingId });
    }

    @Override
    protected List<Booking> extractData(ResultSet rs) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        while (rs.next()) {
            Booking booking = new Booking();
            booking.setId(rs.getInt("booking_id"));
            booking.setActive(rs.getBoolean("is_active"));

            Flight flight = new Flight();
            flight.setId(rs.getInt("flight_id"));
            flight.setDepartureTime(dateTimeFromString(rs.getString("departure_time")));

            FlightBooking flightBooking = new FlightBooking();
            flightBooking.setBooking(booking);
            flightBooking.setFlight(flight);
            booking.setFlightBooking(flightBooking);

            Route route = new Route();
            route.setId(rs.getInt("route_id"));
            flight.setRoute(route);

            Airport origin = new Airport();
            origin.setIataId(rs.getString("origin_id"));
            origin.setCity(rs.getString("origin_city"));
            route.setOrigin(origin);

            Airport destination = new Airport();
            destination.setIataId(rs.getString("destination_id"));
            destination.setCity(rs.getString("destination_city"));
            route.setDestination(destination);

            bookings.add(booking);
        }
        return bookings;
    }
}
