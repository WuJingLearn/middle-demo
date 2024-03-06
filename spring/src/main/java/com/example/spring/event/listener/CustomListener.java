package com.example.spring.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
// 异步执行方法
@Async
public class CustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("当前的线程:"+Thread.currentThread
                ());
        System.out.println("CustomListener收到事件:" + event);
    }
}
