package org.javaboy.middleware.impl2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtil {


    private static final Logger LOG = LoggerFactory.getLogger(JdbcUtil.class);

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error("JdbcUtil close connection error", e);
        }
    }

}
