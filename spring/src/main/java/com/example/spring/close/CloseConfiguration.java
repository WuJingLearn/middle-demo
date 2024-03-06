package com.example.spring.close;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.jdbc.Clob;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.PreparedStatement;

/**
 * @author:majin.wj
 *
 * 普通的spring 容器，是没有注册jvm shutdown 钩子的。需要手动注册。
 * 在springboot中，是自动注册的
 */

@Configuration
@ComponentScan(value = {"com.example.spring.close"})
public class CloseConfiguration {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CloseConfiguration.class);

//        context.close();

        context.registerShutdownHook();
    }


    void test( ) {

        PreparedStatement statement = null;
//        statement.setBlob(1,new Blob());
//        statement.setClob(1,new Clob());
//        statement.setTin
//        statement.setShort();
    }

}
