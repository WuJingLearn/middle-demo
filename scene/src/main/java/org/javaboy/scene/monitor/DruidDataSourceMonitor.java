package org.javaboy.scene.monitor;

import com.alibaba.druid.pool.DruidDataSource;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * spring-actuator中集成的度量统计API使用的框架是Micrometer
 */
@ConditionalOnClass(DruidDataSource.class)
@Configuration
public class DruidDataSourceMonitor extends SpringBeanMonitor<DruidDataSource>{

    @Autowired
    private MeterRegistry meterRegistry;


    public void init() {
        Map<String, DruidDataSource> beanOfType = getBeanOfType(DruidDataSource.class);

        meterRegistry.counter("duird.datasource.minIdle", "");
    }


}
