package com.ss.utopia.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractBaseRepository<T>  {

    private final Connection connection;

    protected AbstractBaseRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(String sql, Object[] values) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        setStatementValues(ps, values);
        ps.executeUpdate();
    }

    public Integer saveReturnPk(String sql, Object[] values) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
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

//    public T findOne(String sql, Object[] values) throws SQLException {
//        PreparedStatement ps = connection.prepareStatement(sql);
//        setStatementValues(ps, values);
//        ResultSet rs = ps.executeQuery();
//        return extractData(rs).get(0);
//    }

    private void setStatementValues(PreparedStatement ps, Object[] values) throws SQLException {
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }
        }
    }

    protected abstract List<T> extractData(ResultSet rs) throws SQLException;
}
