package com.example.spring.properties.initialize;

import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author:majin.wj
 */

@Component
public class ExternalPropertyContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        System.out.println("ExternalPropertyContextInitializer 初始化");
      /*  PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();
        try {
            PropertySource<?> propertySource = loader.load("publicConfiguration",
                    new InputStreamResource(Files.newInputStream(Paths.get("C:\\Users\\fkp12\\Desktop\\application.properties")))).get(0);
            applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

    }
}
