package com.ss.utopia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.domain.Airplane;

public class AirplaneRepository extends AbstractBaseRepository<Airplane> {

    @Override
    protected List<Airplane> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
