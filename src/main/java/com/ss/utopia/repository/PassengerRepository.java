package com.ss.utopia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.domain.Passenger;

public class PassengerRepository extends AbstractBaseRepository<Passenger> {

    @Override
    protected List<Passenger> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
