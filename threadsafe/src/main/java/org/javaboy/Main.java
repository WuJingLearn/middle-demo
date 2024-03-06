package org.javaboy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author majin.wj
 * @date 2023/6/11 2:57 PM
 * @desc
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {


        CountDownLatch latch = new CountDownLatch(1);


        for (int i = 0; i < 10; i++) {
            new Thread(()->{

                try {
                    boolean a = latch.await(10, TimeUnit.SECONDS);
                    System.out.println(a);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }).start();
        }

        Thread.sleep(1000);

        System.out.println();

        latch.countDown();

    }
}