package org.javaboy.utils;

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal.withInitial(()->{
            return "zs";
        });
    }

}
