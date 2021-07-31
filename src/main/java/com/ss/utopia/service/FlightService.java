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

    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public List<Flight> getFlights() {
        try {
            return repository.findAllFlights();
        } catch (SQLException ex) {
            logger.info("Problem loading flights: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }
}
