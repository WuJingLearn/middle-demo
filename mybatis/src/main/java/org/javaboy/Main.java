package org.javaboy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author majin.wj
 * @date 2023/6/9 10:15 AM
 * @desc
 */
@MapperScan(basePackages = {"org.javaboy.mybatis.example"})
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
