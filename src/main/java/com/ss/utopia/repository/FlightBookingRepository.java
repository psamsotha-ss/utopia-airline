package com.ss.utopia.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.domain.FlightBooking;

public class FlightBookingRepository extends AbstractBaseRepository<FlightBooking> {

    public FlightBookingRepository() {}

    public FlightBookingRepository(Connection connection) {
        super(connection);
    }

    public void createFlightBooking(Integer flightId, Integer bookingId) throws SQLException {
        final String sql = "INSERT INTO flight_bookings (flight_id, booking_id) VALUES (?, ?)";
        save(sql, new Object[] { flightId, bookingId });
    }

    @Override
    protected List<FlightBooking> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
