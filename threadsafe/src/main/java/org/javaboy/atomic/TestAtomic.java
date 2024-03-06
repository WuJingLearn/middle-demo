package org.javaboy.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author majin.wj
 * @date 2023/6/26 8:22 PM
 * @desc
 */
public class TestAtomic {


    public static void main(String[] args) throws InterruptedException {

        testAtomic();


    }

    static void testAtomic() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);

        CountDownLatch latch = new CountDownLatch(100);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    integer.getAndIncrement();
                }
                latch.countDown();;
            });
            thread.start();
        }

        latch.await();

        System.out.println(System.currentTimeMillis()-start);
        System.out.println(integer.get());
    }

    static class Num{
        int num;

        void incr() {
            this.num++;
        }

        void printNum(){
            System.out.println(num);
        }
    }

    public static void testSyn() throws InterruptedException {
        Object lock = new Object();
        Num num = new Num();
        CountDownLatch latch = new CountDownLatch(100);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                synchronized (lock) {
                    for (int j = 0; j < 1000; j++) {
                        num.incr();;
                    }
                }

                latch.countDown();;
            });
            thread.start();
        }

        latch.await();

        System.out.println(System.currentTimeMillis()-start);

        num.printNum();;
    }
}
