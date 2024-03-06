package org.javaboy.zookeeper.distribute;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author majin.wj
 * @date 2023/5/27 11:51 AM
 * @desc
 */
public class Test {


    /**
     *
     * 读锁  ---- 写锁（阻塞） -- 读锁（写锁后面上的读锁是阻塞的）
     *
     * @param args
     */
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();


        new Thread(() -> {

            readLock.lock();

            System.out.println(Thread.currentThread().getName() + "读取数据");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "读完数据");
            readLock.unlock();

        }, "thread1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {

            readLock.lock();

            System.out.println(Thread.currentThread().getName() + "读取数据");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "读完数据");
            readLock.unlock();

        }, "thread4").start();

        new Thread(() -> {

            writeLock.lock();

            System.out.println(Thread.currentThread().getName() + "修改数据");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "修改数据");
            writeLock.unlock();
        }, "thread2").start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {

            readLock.lock();

            System.out.println(Thread.currentThread().getName() + "读取数据");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "读完数据");

            readLock.unlock();

        }, "thread3").start();


    }
}
