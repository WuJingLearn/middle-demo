package org.javaboy.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class ConditionDemo2 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();


        new Thread(()->{
            lock.lock();

            try {
                System.out.println("生产者等待");
                condition.await();
                System.out.println("生产者继续执行");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            lock.unlock();;
        }).start();

        Thread.sleep(1000);

        lock.lock();
        /**
         * 唤醒之后，生产者先从同步队列中移除，但是此时锁依然被main线程占用
         * 生产者线程又加入了阻塞队列
         */
        condition.signal();
        System.out.println("唤醒生产者");
        lock.unlock();;

    }

}
