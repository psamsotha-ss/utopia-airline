package com.ss.utopia.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ss.utopia.domain.User;
import com.ss.utopia.repository.UserRepository;

public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllEmployees() {
        try {
            return repository.findUsersByRoleName("Employee");
        } catch (SQLException ex) {
            logger.error("Could not get employees: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }
}
