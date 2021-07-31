package com.ss.utopia.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.repository.AirportRepository;

public class AirportService {

    private static final Logger logger = LogManager.getLogger(AirportService.class);

    private final AirportRepository repository;

    public AirportService(AirportRepository repository) {
        this.repository = repository;
    }

    public List<Airport> getAllAirports() {
        try {
            return repository.findAllAirports();
        } catch (SQLException ex) {
            logger.error("Could not read airports: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }
}
