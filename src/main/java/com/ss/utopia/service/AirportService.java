package com.ss.utopia.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.repository.AirportRepository;

public class AirportService {

    private static final Logger logger = LoggerFactory.getLogger(AirportService.class);

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

    public void deleteAirport(Airport airport) throws PersistenceException  {
        try {
            repository.deleteAirport(airport.getIataId());
        } catch (SQLException ex) {
            logger.error("Could not delete airport: {}", ex.getMessage());
            throw new PersistenceException("Could not delete airport", ex);
        }
    }

    public void createAirport(Airport airport) throws PersistenceException {
        try {
            repository.createAirport(airport.getIataId(), airport.getCity());
        } catch (SQLException ex) {
            logger.error("Could not create airport: {}", ex.getMessage());
            throw new PersistenceException("Could not create airport", ex);
        }
    }
}
