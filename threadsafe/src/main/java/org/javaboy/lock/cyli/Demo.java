package org.javaboy.lock.cyli;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Cyclic:循环的栅栏
 */
public class Demo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        /**
         * ReentrantLock + count
         * 采用两个一批两个一批的方式。第三个线程在执行await的时候，又会阻塞；
         * 在count=0的那个线程中，会将count重新赋值为2
         */
        CyclicBarrier barrier = new CyclicBarrier(2);

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                try {
                    barrier.await();
                    System.out.println("开始执行");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        }

    }
}
