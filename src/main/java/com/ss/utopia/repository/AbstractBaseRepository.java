package com.ss.utopia.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ss.utopia.db.DatabaseManager;

public abstract class AbstractBaseRepository<T>  {

    private static final Logger logger = LogManager.getLogger(AbstractBaseRepository.class);

    private final Connection connection;

    protected AbstractBaseRepository() {
        try {
            this.connection = DatabaseManager.getInstance().getDataSource().getConnection();
        } catch (SQLException ex) {
            logger.error("Could not establish connection: {}", ex.getMessage());
            throw new IllegalStateException("Could not establish connection", ex);
        }
    }

    protected AbstractBaseRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(String sql, Object[] values) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        setStatementValues(ps, values);
        ps.executeUpdate();
    }

    public Integer saveReturnPk(String sql, Object[] values) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        setStatementValues(ps, values);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        return rs.next() ? rs.getInt(1) : null;
    }

    public List<T> find(String sql, Object[] values) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        setStatementValues(ps, values);
        ResultSet rs = ps.executeQuery();
        return extractData(rs);
    }

    public Object findSingleItem(String sql, Object[] values) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        setStatementValues(ps, values);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getObject(1);
        }
        return null;
    }

    public void delete(String sql, Object[] values) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        setStatementValues(ps, values);
        ps.execute();
    }

    private void setStatementValues(PreparedStatement ps, Object[] values) throws SQLException {
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }
        }
    }

    public int booleanToInt(boolean isTrue) {
        return isTrue ? 1 : 0;
    }

    protected abstract List<T> extractData(ResultSet rs) throws SQLException;
}
