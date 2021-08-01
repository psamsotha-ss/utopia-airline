package com.ss.utopia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.User;
import com.ss.utopia.domain.UserRole;

public class UserRepository extends AbstractBaseRepository<User> {

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
