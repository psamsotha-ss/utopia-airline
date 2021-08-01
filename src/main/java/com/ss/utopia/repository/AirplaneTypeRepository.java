package com.ss.utopia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.AirplaneType;

public class AirplaneTypeRepository extends AbstractBaseRepository<AirplaneType> {

    public List<AirplaneType> findAllTypes() throws SQLException {
        final String sql = "SELECT id, max_capacity FROM airplane_type";
        return find(sql, null);
    }

    public void updateCapacity(Integer typeId, Integer capacity) throws SQLException {
        final String sql = "UPDATE airplane_type SET max_capacity = ? WHERE id = ?";
        save(sql, new Object[] { typeId, capacity });
    }

    @Override
    protected List<AirplaneType> extractData(ResultSet rs) throws SQLException {
        List<AirplaneType> types = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            int capacity = rs.getInt("max_capacity");
            types.add(new AirplaneType(id, capacity));
        }
        return types;
    }
}
