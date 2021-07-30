package com.ss.utopia.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ss.utopia.db.DatabaseException;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Route;
import com.ss.utopia.util.Converters;

public class JdbcFlightRepository implements FlightRepository {

    private static final Logger logger = LogManager.getLogger(JdbcFlightRepository.class);

    private final DataSource dataSource;

    public JdbcFlightRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Flight> findAll() {
        final String query = "SELECT f.id, f.departure_time, f.reserved_seats, f.seat_price, r.id, o.iata_id, o.city, d.iata_id, d.city " +
                "FROM flight f " +
                "JOIN route r ON f.route_id = r.id " +
                "JOIN airport o ON r.origin_id = o.iata_id " +
                "JOIN airport d ON r.destination_id = d.iata_id " +
                "ORDER BY r.id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            List<Flight> flights = new ArrayList<>();
            while (rs.next()) {
                String originId = rs.getString(6);
                String originCity = rs.getString(7);
                Airport origin = new Airport(originId, originCity);

                String destId = rs.getString(8);
                String destCity = rs.getNString(9);
                Airport dest = new Airport(destId, destCity);

                int routeId = rs.getInt(5);
                Route route = new Route(routeId, origin, dest);

                int flightId = rs.getInt(1);
                String departTime = rs.getString(2);
                int resSeats = rs.getInt(3);
                float price = rs.getFloat(4);

                Flight flight = new Flight();
                flight.setId(flightId);
                flight.setRoute(route);
                flight.setReservedSeats(resSeats);
                flight.setSeatPrice(BigDecimal.valueOf(price));
                flight.setDepartureTime(Converters.dateFromString(departTime));

                flights.add(flight);
            }
            return flights;
        } catch (SQLException ex) {
            logger.info("Could not load flights: {}", ex.getMessage());
            throw new DatabaseException("Could not load flights", ex);
        }
    }
}
