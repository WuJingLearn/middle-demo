package org.javaboy.boot.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author majin.wj
 * @date 2023/6/29 10:16 AM
 * @desc
 */

//会优先让Config2先自动状态.该注解只会在自动装配阶段才会生效。
@AutoConfigureAfter(Config2AutoConfiguration.class)
@Configuration
public class Config1AutoConfiguration {

    @PostConstruct
    public void init(){
        System.out.println("Config1AutoConfiguration init");
    }
}
