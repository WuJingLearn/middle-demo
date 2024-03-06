package org.javaboy.future.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * @author:majin.wj
 */
public class Demo2 {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = Arrays.asList("zs", "ls");
        CompletableFuture[] array = list.stream().map(s -> CompletableFuture.runAsync(() -> {
            System.out.println("执行" + s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        })).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(array).join();
        System.out.println("end");

    }
}
