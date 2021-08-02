package com.ss.utopia.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.domain.BookingUser;

public class BookingUserRepository extends AbstractBaseRepository<BookingUser> {

    public BookingUserRepository() {}

    public BookingUserRepository(Connection connection) {
        super(connection);
    }

    public void createBookingUser(Integer bookingId, Integer userId) throws SQLException {
        final String sql = "INSERT INTO booking_user (booking_id, user_id) VALUES (?, ?)";
        save(sql, new Object[] { bookingId, userId });
    }

    @Override
    protected List<BookingUser> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
