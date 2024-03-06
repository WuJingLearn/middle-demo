package org.javaboy.middleware.impl1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author majin.wj
 * @date 2023/5/9 4:17 PM
 * @desc
 */
public class Test {


    public static void main(String[] args) throws SQLException {
        String username = "root";
        String password = "root1234";
        String url = "jdbc:mysql://localhost:3306/student?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
        FastDataSource fastDataSource = new FastDataSource(1, 10, 10, username, password, url);
        Connection connection = fastDataSource.getConnection();
        Connection connection2 = fastDataSource.getConnection();
        Connection connection3 = fastDataSource.getConnection();
        Connection connection4 = fastDataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from a");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet);
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            Integer score = resultSet.getInt("score");
            System.out.println("name:" + name + "score:" + score);
        }

        System.out.println(fastDataSource.getActiveCount());
        System.out.println(fastDataSource.getPoolCount());

        connection.commit();

        System.out.println(fastDataSource.getActiveCount());
        System.out.println(fastDataSource.getPoolCount());
    }
}
