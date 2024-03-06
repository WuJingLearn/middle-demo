package org.javaboy.middleware.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DruidTest {

    public static void main(String[] arg) {
        DruidDataSource dataSource = new DruidDataSource();

        // 最小连接数
        dataSource.setMinIdle(5);
        // 最大连接数
        dataSource.setMaxActive(10);
        // 初始连接数
        dataSource.setInitialSize(5);
        // 获取连接最大等待时间3000毫秒
        dataSource.setMaxWait(3000);
        dataSource.setMaxWaitThreadCount(1);
        // 30分钟 配置连接最小生存时间；
        // 空闲连接就是PoolCount的数量，当空闲数超过了minIdle，那么会淘汰poolCount-minIdle个连接
        // 前提是连接的空闲时间达到了最小淘特时间
        dataSource.setMinEvictableIdleTimeMillis(30 * 60 * 1000);
        // 60分钟 配置连接最大生存时间；
        // 首先会淘汰poolCount-minIdle个连接，但是如果连接的空闲时间超过了MaxEvictableIdleTime，该连接也会被
        // Evict 驱逐
        dataSource.setMaxEvictableIdleTimeMillis(60 * 60 * 1000);
        // 设置检测空闲连接间隔市场
        dataSource.setTimeBetweenEvictionRunsMillis(60 * 1000);
        // 设置在获取连接,归还连接时,检测连接是否存活，通过validationQuery指定的sql语句
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1 from a");

        StatFilter statFilter = new StatFilter();
        //配置打印慢sql
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(1);
        List<Filter> filters = new ArrayList<>();
        filters.add(statFilter);
        dataSource.setProxyFilters(filters);
        String username = "root";
        String password = "root1234";
        String url = "jdbc:mysql://localhost:3306/student?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        try {
            DruidPooledConnection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from a where score = ?");
            preparedStatement.setInt(1, 10);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                System.out.println(name);
                Integer score = resultSet.getInt(2);
                System.out.println(score);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        Map<String, Object> statData = dataSource.getStatData();
        System.out.println(statData);

    }

}
