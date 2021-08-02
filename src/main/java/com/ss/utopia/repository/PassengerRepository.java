package com.ss.utopia.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.domain.Passenger;

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

    @Override
    protected List<Passenger> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
