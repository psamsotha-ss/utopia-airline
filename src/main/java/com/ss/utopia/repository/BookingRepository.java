package com.ss.utopia.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.domain.Booking;

public class BookingRepository extends AbstractBaseRepository<Booking> {

    public BookingRepository() {}

    public BookingRepository(Connection connection) {
        super(connection);
    }

    public Integer createBooking(Boolean isActive, String confirmationCode) throws SQLException {
        final String sql = "INSERT INTO booking (is_active, confirmation_code) VALUES (?, ?)";
        return saveReturnPk(sql, new Object[] { isActive, confirmationCode });
    }

    @Override
    protected List<Booking> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
