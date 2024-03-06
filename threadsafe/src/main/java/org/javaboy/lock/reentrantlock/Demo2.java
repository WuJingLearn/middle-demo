package org.javaboy.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * ReentrantLock 通过AQS中state变量来记录锁的获取状态
 * state: state等于0的时候，表示没有线程获取到锁，大于0表示有线程获取到锁，ReentrantLock是可重入的锁,
 * 是几，表示这个锁已经被重入了几次
 *
 *
 *  unlock: 会唤醒阻塞队列中第一个被阻塞的线程
 *
 */
public class Demo2 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);


        /**
         * ConditionObject 条件对象也是定义在AQS类中的
         */
        Condition condition = lock.newCondition();

        try {
            lock.lock();
        }finally {
            lock.unlock();
        }

    }

}
