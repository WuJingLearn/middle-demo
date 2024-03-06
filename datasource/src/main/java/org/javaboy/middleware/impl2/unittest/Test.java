package org.javaboy.middleware.impl2.unittest;

import org.javaboy.middleware.impl2.TCConnection;
import org.javaboy.middleware.impl2.TCDataSource;
import org.javaboy.middleware.impl2.filter.LogFilter;
import org.javaboy.middleware.impl2.filter.StatFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {


    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true";
        TCDataSource datasource = new TCDataSource();
        datasource.setUrl(url);
        datasource.setPassword("root1234");
        datasource.setUsername("root");

        datasource.setMaxActive(10);
        datasource.setMaxWaitThreadCount(10);

        datasource.addFilter(new LogFilter());
        datasource.addFilter(new StatFilter());
        test1(datasource.getConnection());

//        TCConnection connection = datasource.getConnection();
//        PreparedStatement prepared = connection.prepareStatement("select * from a where score = ?");
    }

    static void test2(TCDataSource dataSource) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    TCConnection connection = dataSource.getConnection();
                    System.out.println(connection);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }).start();
        }
    }


    static void test1(TCConnection connection) throws SQLException {
        System.out.println(connection.getRawConnection());
        PreparedStatement statement = connection.prepareStatement("select * from a where score = ?");
        statement.setInt(1, 100);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String name = result.getString(1);
            int age = result.getInt(2);
            System.out.println("name:" + name + ",age:" + age);
        }
    }

    static void test2() {

    }

}
