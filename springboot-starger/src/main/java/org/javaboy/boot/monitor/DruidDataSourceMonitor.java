package org.javaboy.boot.monitor;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * spring-actuator中集成的度量统计API使用的框架是Micrometer
 */
@ConditionalOnClass(DruidDataSource.class)
@Configuration
public class DruidDataSourceMonitor extends SpringBeanMonitor<DruidDataSource> {


    @PostConstruct
    public void init() {
        Map<String, DruidDataSource> beanMap = getBeanOfType(DruidDataSource.class);
        System.out.println("druid bean信息:" + beanMap);
    }


}
