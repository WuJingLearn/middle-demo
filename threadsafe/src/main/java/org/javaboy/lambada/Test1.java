package org.javaboy.lambada;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Test1 {

    public static void main(String[] args) {
        Arrays.asList("zs","ls").stream().map(item->CompletableFuture.supplyAsync(()->{
            return item.toUpperCase();
        })).collect(Collectors.toList()).stream().map(CompletableFuture::join);
    }

}
