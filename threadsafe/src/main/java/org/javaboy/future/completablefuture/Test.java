package org.javaboy.future.completablefuture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("zs");
        strList.add("ls");
        strList.add("ww");

        System.out.println("start:" + new Date());
        List<String> upper = toUpper(strList);
        System.out.println("end:" + new Date());
        System.out.println(upper);

    }

    static List<String> toUpper(List<String> strList) {
        if (strList == null) {
            return null;
        }
        List<String> result = strList.stream().map(str -> {
                    return CompletableFuture.supplyAsync(() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("str.toUpperCase()");
                        return str.toUpperCase();
                    });
                }).collect(Collectors.toList()) // 只有当遇到结束算子，map中才会开始执行
                .stream().map(CompletableFuture::join).collect(Collectors.toList());
        return result;
    }

}
