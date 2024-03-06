package org.javaboy.future.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author:majin.wj
 * 所有都执行完成；
 */
public class AllOfDemo {

    static ThreadPoolExecutor executor  =(ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("task 1");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "task1";
                },executor),
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("task 2");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "task2";
                },executor),
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("task 3");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "task3";
                },executor));
        CompletableFuture<Boolean> booleanCompletableFuture = allFuture.thenApply(r -> {
            System.out.println("都执行完了" + r);
            return true;
        });

    }

}
