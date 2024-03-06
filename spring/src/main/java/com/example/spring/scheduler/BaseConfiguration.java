package com.example.spring.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author:majin.wj
 */

@Configuration
@EnableScheduling
public class BaseConfiguration {

//    @Scheduled(cron = "0/1 * * * * *")
    public void execute() {
        System.out.println("执行定时任务");
    }

}
