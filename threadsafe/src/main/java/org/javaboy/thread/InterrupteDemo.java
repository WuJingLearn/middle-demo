package org.javaboy.thread;

import java.util.concurrent.FutureTask;

/**
 * 使用中断，来优雅的停止线程
 */
public class InterrupteDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().interrupt();;

        // sleep会响应中断，无论在之前还是后调用中断，都可以响应
        Thread.sleep(1000);


    }
}
