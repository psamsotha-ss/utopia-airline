package com.ss.utopia.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.repository.AirplaneRepository;
import com.ss.utopia.repository.AirplaneTypeRepository;

public class AirplaneService {

    private static final Logger logger = LoggerFactory.getLogger(AirplaneService.class);

    private final AirplaneTypeRepository typeRepository;

    public AirplaneService(AirplaneRepository planeRepository, AirplaneTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<AirplaneType> getAllTypes() {
        try {
            return typeRepository.findAllTypes();
        } catch (SQLException ex) {
            logger.error("Couldn't get airplane types: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    public AirplaneType updatePlaneTypeCapacity(AirplaneType type, int newCapacity) throws PersistenceException {
        try {
            typeRepository.updateCapacity(type.getId(), newCapacity);
            type.setMaxCapacity(newCapacity);
        } catch (SQLException ex) {
            logger.error("Could not update airplane type capacity: {}", ex.getMessage());
        }
        return type;
    }
}
