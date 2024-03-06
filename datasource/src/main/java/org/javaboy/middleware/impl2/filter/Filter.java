package org.javaboy.middleware.impl2.filter;

import org.javaboy.middleware.impl2.proxy.PreparedStatementProxy;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Filter {

    ResultSet executeQuery(FilterChain chain, PreparedStatementProxy statement) throws SQLException;

    int executeUpdate(FilterChainImpl filterChain, PreparedStatementProxy preparedStatement) throws SQLException;
}
