package org.javaboy.lock.countdown;

import java.util.concurrent.CountDownLatch;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(1);
        // 自旋的方式修改state的值
        countDown.countDown();


        countDown.await();

    }
}
