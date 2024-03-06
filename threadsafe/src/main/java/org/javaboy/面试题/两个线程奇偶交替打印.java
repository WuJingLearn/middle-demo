package org.javaboy.面试题;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:majin.wj
 */
public class 两个线程奇偶交替打印 {

    public static void main(String[] args) {
        test2(10);
    }


    static void test2(int n) {

        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);

        new Thread(() -> {
            int i = 0;
            try {
                while (i < n) {
                    s1.acquire();
                    System.out.println(i);
                    i += 2;
                    s2.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            int i = 1;
            try {
                while (i < n) {
                    s2.acquire();
                    System.out.println(i);
                    i += 2;
                    s1.release();
                }
            } catch (Exception e) {

            }
        }).start();
    }

    static void test(int n) {
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        new Thread(() -> {
            int i = 0;
            lock.lock();
            try {
                while (i < n) {
                    ;
                    System.out.println(i);
                    c2.signalAll();
                    c1.await();
                    i += 2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            int i = 1;
            lock.lock();
            try {
                while (i < n) {
                    System.out.println(i);
                    i += 2;
                    c1.signalAll();
                    c2.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
