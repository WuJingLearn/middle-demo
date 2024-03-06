package com.example.spring.boot.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author majin.wj
 * @date 2023/6/29 10:02 AM
 * @desc AutoConfigureAfter注解，只会在自动配置jar包中生效。
 * 在普通的项目中是不生效的
 */

//@AutoConfigureAfter(value = Config2.class)
//@Configuration
//@ConditionalOnClass(Map.class)
//@ConditionalOnProperty
@Configuration
@Conditional(value = TestCondition.class)
public class Config1 {

    @PostConstruct
    public void init() {
        System.out.println("Config1 init");
    }

    public static void main(String[] args) {
        System.out.println("他吗的");
    }
}
