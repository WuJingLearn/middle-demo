package org.javaboy.middleware.impl2.filter;

import org.javaboy.middleware.impl2.proxy.PreparedStatementProxy;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface FilterChain {

    public ResultSet executeQuery(PreparedStatementProxy preparedStatement) throws SQLException;

    public int executeUpdate(PreparedStatementProxy preparedStatement) throws SQLException;



}
