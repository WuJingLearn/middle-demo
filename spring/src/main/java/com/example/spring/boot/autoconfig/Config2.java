package com.example.spring.boot.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author majin.wj
 * @date 2023/6/29 10:03 AM
 * @desc
 */
@Configuration
public class Config2 {


    @ConditionalOnBean(name = "dog")
    @Bean
    public Stu stu(){
        System.out.println("Config2中的stu");
       return new Stu();
    }


    @PostConstruct
    public void init(){
        System.out.println("Config2 init");
    }
}
