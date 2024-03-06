package org.javaboy.future.completablefuture;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Demo1 {

    public static void main(String[] args) {
        System.out.println("start: " + new Date());
        test2();
        System.out.println("end: " + new Date());
    }

    static void test1() {
        CompletableFuture<String> future;

        future = new CompletableFuture<>();
        future.complete("zs");

        CompletableFuture<String> future1 = future.whenComplete((r, t) -> {
            System.out.println(t);
            System.out.println(r);
        });
    }

    static void test2() {
        List<String> list = Arrays.asList("zs", "ls", "ww");

        List<CompletableFuture<String>> collect = list.stream().map(item -> {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "hello:" + item.toUpperCase();
            });
        }).collect(Collectors.toList());
        List<String> collect1 = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());

    }


}
