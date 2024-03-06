package com.example.spring.event.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async
@Component
public class HelloAsync {


    public void test(){
        System.out.println("异步执行test:"+Thread.currentThread());
    }
}
