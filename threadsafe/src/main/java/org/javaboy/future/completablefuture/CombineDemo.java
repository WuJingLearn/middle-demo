package org.javaboy.future.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author:majin.wj
 * thenCombine: 两个都执行完成，可以拿到两个返回值
 */
public class CombineDemo {

    static ThreadPoolExecutor executor  =(ThreadPoolExecutor)Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        CompletableFuture<String> 执行task3 = CompletableFuture.supplyAsync(() -> {

            System.out.println("task 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task1";
        }, executor).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("task 2");

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task2";
        }), (r1, r2) -> {
            System.out.println("执行task3");
            return "res2";
        });

    }

}
