package org.javaboy.gc;

import java.util.HashMap;

public class Test {


    /**
     * -Xms20m 堆初始化内存
     * -Xmx20m
     * -Xmn10m 年轻代内存，那么老年代就是xms-xmn 为10m
     * -XX:+UseConcMarkSweepGC
     * -Xloggc:/Users/jingmac/IdeaProjects/middleware-sample/threadsafe/src/main/java/org/javaboy/gc/log.log
     * -XX:+PrintGCDetails
     * -XX:+PrintGCDateStamps
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=/Users/jingmac/IdeaProjects/middleware-sample/threadsafe/src/main/java/org/javaboy/gcgc/heap.prof
     *   -Xms20m -Xmx20m -Xmn10m -XX:+UseConcMarkSweepGC -Xloggc:/Users/jingmac/IdeaProjects/middleware-sample/threadsafe/src/main/java/org/javaboy/gc/log.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/jingmac/IdeaProjects/middleware-sample/threadsafe/src/main/java/org/javaboy/gc/heap.prof
     *
     * @param args
     */

    static HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("zs");

        int i = 1;
        while (true) {
            byte[] bytes = new byte[1024 * 2024];
            map.put("name" + i, "zs");
//            Thread.sleep(100);
            System.out.println("开始");
        }

    }
}
