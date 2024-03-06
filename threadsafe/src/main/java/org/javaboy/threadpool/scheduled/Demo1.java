package org.javaboy.threadpool.scheduled;

import java.sql.Time;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo1 {


    public static void main(String[] args) {
        /**
         * DelayedWorkQueue
         */
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

        executor.scheduleAtFixedRate(()->{
            System.out.println("执行任务");
        },3,3,TimeUnit.SECONDS);
    }

}
