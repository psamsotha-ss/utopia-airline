package com.ss.utopia.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ss.utopia.db.PersistenceException;
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

    public void deleteUser(User user) {
        try {
            repository.deleteUser(user.getId());
        } catch (SQLException ex) {
            logger.error("Could not delete user: {}", ex.getMessage());
            throw new PersistenceException("Could not delete user", ex);
        }
    }

    public void updateUserField(User user, String field, Object value) throws PersistenceException {
        try {
            repository.updateUserField(user.getId(), field, value);
        } catch (SQLException ex) {
            logger.error("Could not update user {} field {} with value '{}': {}",  user, field, value, ex.getMessage());
            throw new PersistenceException("Could not update user field", ex);
        }
    }

    /**
     * Create a new employee
     * @param emp the employee {@code User} object
     * @return the updated employee obj with the generated id
     * @throws PersistenceException if the user could not be created or the Employee role could be be found
     * @throws IllegalArgumentException if the user object contains an id
     */
    public User createNewEmployee(User emp) throws PersistenceException, IllegalArgumentException {
        if (emp.getId() != null) {
            throw new IllegalArgumentException("User cannot have an id.");
        }
        try {
            Integer roleId = repository.findRoleIdByName("Employee");
            if (roleId == null) {
                logger.info("Employee role name does not exist.");
                throw new PersistenceException("Employee role name does not exist.");
            }
            Integer userId = repository.createNewUserWithRole(roleId, emp.getGivenName(), emp.getFamilyName(),
                    emp.getUsername(), emp.getPassword(), emp.getEmail(), emp.getPhone());
            emp.setId(userId);
        } catch (SQLException ex) {
            logger.error("Could not create user: {}", ex.getMessage());
            throw new PersistenceException("Employee could not be created", ex);
        }
        return emp;
    }

    public boolean checkPhoneNumberExists(String number) throws PersistenceException {
        try {
            String dbNumber = repository.findPhoneNumber(number);
            if (dbNumber == null) return false;
        } catch (SQLException ex) {
            logger.error("Could not get phone number: {}", ex.getMessage());
        }
        return true;
    }
}
