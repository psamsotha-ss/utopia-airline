package com.ss.utopia.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.User;
import com.ss.utopia.domain.UserRole;

public class UserRepository extends AbstractBaseRepository<User> {

    public UserRepository() {}

    public UserRepository(Connection connection) {
        super(connection);
    }

    public List<User> findUsersByRoleName(String roleName) throws SQLException {
        final String sql = "SELECT u.id, u.role_id, u.given_name, u.family_name, u.username, " +
                "u.email, u.password, u.phone, r.name AS role_name " +
                "FROM user u " +
                "JOIN user_role r ON u.role_id = r.id " +
                "WHERE r.name = ?";
        return find(sql, new Object[] { roleName });
    }

    public void deleteUser(Integer userId) throws SQLException {
        final String sql = "DELETE FROM user WHERE id = ?";
        delete(sql, new Object[] { userId });
    }

    public void updateUserField(Integer userId, String field, Object value) throws SQLException {
        final String sql = "UPDATE user SET " + field + " = ? WHERE id = ?";
        save(sql, new Object[] { value, userId });
    }

    public Integer createNewUserWithRole(Integer roleId, String givenName, String familyName, String username,
                                         String password, String email, String phone) throws SQLException {
        final String sql = "INSERT INTO user (role_id, given_name, family_name, username, password, email, phone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return saveReturnPk(sql, new Object[] { roleId, givenName, familyName, username, password, email, phone });
    }

    public String findPhoneNumber(String phoneNumber) throws SQLException {
        final String sql = "SELECT phone FROM user WHERE phone = ?";
        Object result = findSingleItem(sql, new Object[] { phoneNumber });
        if (result != null) {
            return (String) result;
        }
        return null;
    }

    public Integer findRoleIdByName(String roleName) throws SQLException {
        final String sql = "SELECT id FROM user_role WHERE name = ?";
        Object result = findSingleItem(sql, new Object[] { roleName });
        if (result != null) {
            return (result instanceof Long) ? Math.toIntExact((Long) result): (Integer) result;
        }
        return null;
    }

    public User findUserByIdAndRole(Integer id, String roleName) throws SQLException {
        final String sql = "SELECT u.id, u.given_name, u.family_name, u.username, " +
                "u.password, u.email, u.phone, r.name AS role_name, u.role_id " +
                "FROM user u " +
                "JOIN user_role r ON u.role_id = r.id " +
                "WHERE u.id = ? AND r.name = ?";
        List<User> users = find(sql, new Object[] { id, roleName });
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    protected List<User> extractData(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setGivenName(rs.getString("given_name"));
            user.setFamilyName(rs.getString("family_name"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));

            UserRole role = new UserRole();
            role.setId(rs.getInt("role_id"));
            role.setName(rs.getString("role_name"));
            user.setRole(role);

            users.add(user);
        }
        return users;
    }
}
