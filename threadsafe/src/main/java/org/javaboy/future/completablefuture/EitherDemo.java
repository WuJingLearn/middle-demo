package org.javaboy.future.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author:majin.wj
 */
public class EitherDemo {


    static ThreadPoolExecutor executor  =(ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) {


        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("task1");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task1";
        },executor).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("task2");

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task2";
        },executor), (r) -> {
            System.out.println("有一个执行完成" + r);
            return "success";
        });


    }

}
