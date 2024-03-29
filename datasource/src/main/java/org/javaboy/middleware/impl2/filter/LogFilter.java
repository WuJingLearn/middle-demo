package org.javaboy.middleware.impl2.filter;

import org.javaboy.middleware.impl2.proxy.PreparedStatementProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public ResultSet executeQuery(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        LOG.info("sql:{},{} executeQuery", statement.getSql(), statement.getParameters());
        return chain.executeQuery(statement);
    }

    @Override
    public int executeUpdate(FilterChainImpl chain, PreparedStatementProxy statement) throws SQLException {
        LOG.info("sql:{},{} executeQuery", statement.getSql(), statement.getParameters());
        return chain.executeUpdate(statement);
    }
}
