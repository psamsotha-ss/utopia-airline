package com.ss.utopia.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Route;

import static com.ss.utopia.util.Converters.dateTimeFromString;

public class FlightRepository extends AbstractBaseRepository<Flight> {

    public FlightRepository() {}

    public FlightRepository(Connection connection) {
        super(connection);
    }

    public List<Flight> findAllFlights() throws SQLException {
        final String sql = "SELECT f.id AS flight_id, " +
                "       f.departure_time, " +
                "       f.reserved_seats, " +
                "       f.seat_price, " +
                "       r.id AS route_id, " +
                "       o.iata_id AS origin_id, " +
                "       o.city AS origin_city, " +
                "       d.iata_id AS destination_id, " +
                "       d.city AS destination_city, " +
                "       f.airplane_id, " +
                "       type.id AS type_id, " +
                "       type.max_capacity " +
                "FROM flight f " +
                "JOIN airplane a ON a.id = f.airplane_id " +
                "JOIN airplane_type type ON a.type_id = type.id " +
                "JOIN route r ON f.route_id = r.id " +
                "JOIN airport o ON r.origin_id = o.iata_id " +
                "JOIN airport d ON r.destination_id = d.iata_id " +
                "ORDER BY f.id";
        return find(sql, null);
    }

    public void deleteFlight(Flight flight) throws SQLException {
        final String sql = "DELETE FROM flight WHERE id = ?";
        delete(sql, new Object[] { flight.getId() });
    }

    public void updateFlightField(Integer flightId, String field, Object value) throws SQLException {
        final String sql = "UPDATE flight SET " + field + " = ? WHERE id = ?";
        save(sql, new Object[] { value, flightId });
    }

    @Override
    protected List<Flight> extractData(ResultSet rs) throws SQLException {
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {
            Flight flight = new Flight();
            flight.setId(rs.getInt("flight_id"));
            flight.setDepartureTime(dateTimeFromString(rs.getString("departure_time")));
            flight.setReservedSeats(rs.getInt("reserved_seats"));
            flight.setSeatPrice(BigDecimal.valueOf(rs.getFloat("seat_price")));

            Route route = new Route();
            route.setId(rs.getInt("route_id"));

            Airport origin = new Airport();
            origin.setIataId(rs.getString("origin_id"));
            origin.setCity(rs.getString("origin_city"));

            Airport dest = new Airport();
            dest.setIataId(rs.getString("destination_id"));
            dest.setCity(rs.getString("destination_city"));

            Airplane airplane = new Airplane();
            airplane.setId(rs.getInt("airplane_id"));

            AirplaneType type = new AirplaneType();
            type.setId(rs.getInt("type_id"));
            type.setMaxCapacity(rs.getInt("max_capacity"));

            airplane.setType(type);
            route.setOrigin(origin);
            route.setDestination(dest);
            flight.setRoute(route);
            flight.setAirplane(airplane);

            flights.add(flight);
        }
        return flights;
    }
}
