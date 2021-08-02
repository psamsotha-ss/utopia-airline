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
        return getAllUsersByRoleName("Employee");
    }

    public List<User> getAllTravelers() {
        return getAllUsersByRoleName("Traveler");
    }

    /**
     * Get all users by role name
     * @param roleName the role name
     * @return the list of employees with the roleName. Returns an empty list if there is an error.
     */
    public List<User> getAllUsersByRoleName(String roleName) {
        try {
            return repository.findUsersByRoleName(roleName);
        } catch (SQLException ex) {
            logger.error("Could not get users: {}", ex.getMessage());
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

    /**
     * Update a field for a user
     * @param user the user to update
     * @param field the field to update
     * @param value the new value for the field
     * @throws PersistenceException if an error occurs updating the field
     */
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
     * @param employee the employee {@code User} object
     * @return the updated employee obj with the generated id
     * @throws PersistenceException if the user could not be created or the 'Employee' role could be be found
     * @throws IllegalArgumentException if the user object contains an id
     */
    public User createNewEmployee(User employee) throws PersistenceException, IllegalArgumentException {
        return  createNewUserByRoleName(employee, "Employee");
    }

    /**
     * Create a new traveler
     * @param traveler the traveler {@code User} object
     * @return the updated traveler obj with the generated id
     * @throws PersistenceException if the user could not be created or the 'Traveler' role could be be found
     * @throws IllegalArgumentException if the user object contains an id
     */
    public User createNewTraveler(User traveler) throws PersistenceException, IllegalArgumentException {
        return createNewUserByRoleName(traveler, "Traveler");
    }

    /**
     * Create a new user
     * @param user the {@code User} object
     * @param roleName the role name of the user
     * @return the updated user object with the generated id
     * @throws PersistenceException if the user could not be created or the role could be be found
     * @throws IllegalArgumentException if the user object contains an id
     */
    public User createNewUserByRoleName(User user, String roleName) throws PersistenceException, IllegalArgumentException {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User cannot have an id.");
        }
        try {
            Integer roleId = repository.findRoleIdByName(roleName);
            if (roleId == null) {
                logger.info("User role name does not exist.");
                throw new PersistenceException("User role name does not exist.");
            }
            Integer userId = repository.createNewUserWithRole(roleId, user.getGivenName(), user.getFamilyName(),
                    user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone());
            user.setId(userId);
        } catch (SQLException ex) {
            logger.error("Could not create user: {}", ex.getMessage());
            throw new PersistenceException("User could not be created", ex);
        }
        return user;
    }

    /**
     * Get traveler by id
     * @param id the id of the user
     * @return the user if found, or null
     * @throws PersistenceException is there is a problem finding the user
     */
    public User getTraveler(Integer id) {
        try {
            return repository.findUserByIdAndRole(id, "Traveler");
        } catch (SQLException ex) {
            logger.error("Could not get user: {}", ex.getMessage());
            throw new PersistenceException("Could not get user", ex);
        }
    }

    /**
     * Check if a phone number already exists. There cannot be duplicate phone numbers
     * @param number the phone number to check
     * @return true if the number exists, false if not
     * @throws PersistenceException if there is a problem checking the number
     */
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
