package org.javaboy.mybatis.example;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSourceConfig {

    public DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("root1234");
        dataSource.setUrl("jdbc:mysql://localhost:3306/student?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false");
        //最小连接数
        dataSource.setMinIdle(5);
        //最大连接数
        dataSource.setMaxActive(10);
        //初始化连接数
        dataSource.setInitialSize(5);
        //获取连接最大等待时间
        dataSource.setMaxWait(3000);
        //打印日志
        dataSource.addFilters("slf4j");
        //监控过滤器
        StatFilter statFilter = new StatFilter();
        //配置打印慢sql
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(1);
        List<Filter> filters = new ArrayList<>();
        filters.add(statFilter);
        dataSource.setProxyFilters(filters);
        return dataSource;
    }
}
