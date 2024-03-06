package com.example.spring.ioc.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.spring.ioc.context");
        ContextBean bean = context.getBean(ContextBean.class);
        System.out.println(bean);
    }
}
