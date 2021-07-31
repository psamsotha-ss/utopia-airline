package com.ss.utopia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airport;

public class AirportRepository extends AbstractBaseRepository<Airport> {

    public List<Airport> findAllAirports() throws SQLException {
        final String sql = "SELECT * FROM airport";
        return find(sql, null);
    }

    @Override
    protected List<Airport> extractData(ResultSet rs) throws SQLException {
        List<Airport> airports = new ArrayList<>();
        while (rs.next()) {
            String iataId = rs.getString("iata_id");
            String city = rs.getString("city");
            airports.add(new Airport(iataId, city));
        }
        return airports;
    }
}
