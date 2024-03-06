package org.javaboy.boot.autoconfig;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author majin.wj
 * @date 2023/6/29 10:16 AM
 * @desc
 */

@Configuration
public class Config2AutoConfiguration {

    @PostConstruct
    public void init(){
        System.out.println("Config2AutoConfiguration init");
    }
}
