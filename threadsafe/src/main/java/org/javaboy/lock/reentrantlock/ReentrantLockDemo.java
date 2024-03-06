package org.javaboy.lock.reentrantlock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author majin.wj
 * @date 2023/6/11 3:02 PM
 * @desc
 */
public class ReentrantLockDemo {

    static Map<Object, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        String lockKey = "zs";

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                ReentrantLock lock = new ReentrantLock();
                lockMap.putIfAbsent(lockKey, lock);
                lock = lockMap.get(lockKey);
                System.out.println("lock 对象:" + lock);
                boolean get = false;
                try {
                    get = lock.tryLock(10, TimeUnit.SECONDS);
                    if (get) {
                        System.out.println("获取数据");
                        Thread.sleep(2000);
                    } else {
                        System.out.println("获取锁失败");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (get) {
                        lock.unlock();
                    }
                }

            }).start();
        }
    }
}
