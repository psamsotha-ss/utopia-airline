package com.ss.utopia.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Passenger;

import static com.ss.utopia.util.Converters.dateFromString;

public class PassengerRepository extends AbstractBaseRepository<Passenger> {

    public PassengerRepository() {}

    public PassengerRepository(Connection connection) {
        super(connection);
    }

    public Integer createPassenger(Integer bookingId, String givenName, String familyName,
                                   String dob, String gender, String address) throws SQLException {
        final String sql = "INSERT INTO passenger (booking_id, given_name, family_name, dob, gender, address) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return saveReturnPk(sql, new Object[] { bookingId, givenName, familyName, dob, gender, address });
    }

    public Passenger getPassengerByBookingId(Integer bookingId) throws SQLException {
        final String sql = "SELECT id, booking_id, given_name, family_name, dob, gender, address FROM passenger " +
                "WHERE booking_id = ?";
        List<Passenger> passengers = find(sql, new Object[] { bookingId });
        if (passengers.size() > 0) {
            return passengers.get(0);
        }
        return null;
    }

    public void deletePassenger(Integer passengerId) throws SQLException {
        final String sql = "DELETE FROM passenger WHERE id = ?";
        delete(sql, new Object[] { passengerId });
    }

    @Override
    protected List<Passenger> extractData(ResultSet rs) throws SQLException {
        List<Passenger> passengers = new ArrayList<>();
        while (rs.next()) {
            Passenger passenger = new Passenger();
            passenger.setId(rs.getInt("id"));
            passenger.setGivenName(rs.getString("given_name"));
            passenger.setFamilyName(rs.getString("family_name"));
            passenger.setDateOfBirth(dateFromString(rs.getString("dob")));
            passenger.setGender(rs.getString("gender"));
            passenger.setAddress(rs.getString("address"));

            Booking booking = new Booking();
            booking.setId(rs.getInt("booking_id"));
            passenger.setBooking(booking);

            passengers.add(passenger);
        }
        return passengers;
    }
}
