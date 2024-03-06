package org.javaboy.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:majin.wj
 */
public class ShutdownGracefullyTest {

    static ThreadPoolExecutor threadPoolTaskExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
                System.out.println("task execute complete...");
            });
        }

    }
}
