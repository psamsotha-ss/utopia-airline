package com.ss.utopia.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ss.utopia.domain.Flight;
import com.ss.utopia.repository.FlightRepository;

public class FlightService {

    private static final Logger logger = LogManager.getLogger(FlightService.class);

    private final FlightRepository flightRepo;

    public FlightService(FlightRepository repository) {
        this.flightRepo = repository;
    }

    public List<Flight> getAllFlights() {
        try {
            return flightRepo.findAllFlights();
        } catch (SQLException ex) {
            logger.error("Could not read flights: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    public void deleteFlight(Flight flight) {
        try {
            flightRepo.deleteFlight(flight);
        } catch (SQLException ex) {
            logger.error("Could not delete flight: {}: {}", flight, ex.getMessage());
        }
    }

    public void updateFlightField(Flight flight, String field, Object value) {
        try {
            flightRepo.updateFlightField(flight.getId(), field, value);
        } catch (SQLException ex) {
            logger.error("Could not update flight field '{}' with value '{}': {}", field, value, ex);
        }
    }
}
