package com.example.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author majin.wj
 * @date 2023/6/27 9:11 PM
 * @desc
 */
@ComponentScan
public class AnnotationConfig {

    /**
     * 1.DefaultSingletonRegistry 中维护了单列池
     * 2.DefaultListableBeanFactory 继承了DefaultSingletonRegistry;
     * @param args
     */
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Object student = context.getBean("student");
        System.out.println(student);

        ApplicationContext parent = context.getParent();
        System.out.println(parent);
    }
}
