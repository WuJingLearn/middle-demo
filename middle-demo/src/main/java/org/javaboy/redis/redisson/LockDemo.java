package org.javaboy.redis.redisson;


import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Redisson分布式锁的设计:
 * 1.采用了hash结构，key是锁的名称，filed为获取锁的线程id, value是锁的重入次数
 * 2.如果key未创建，或者field是当前的线程id，那么可以获取锁，通过hincrby key filed 1增加锁的重入次数
 */
public class LockDemo {
    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    lock();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

    }


    static void tryLock() {
        // redisson分布式锁使用hash结构，key filed value. key是锁的名称，field是线程id，value是锁的重入次数
        // redisson的重入通过hash结构的方式来做。其实重入锁的实现也可以在应用程序来做。curator的分布式锁就是在应用程序记录的

//        commandExecutor.syncedEval(getRawName(), LongCodec.INSTANCE, command,
//                "if ((redis.call('exists', KEYS[1]) == 0) " +
//                        "or (redis.call('hexists', KEYS[1], ARGV[2]) == 1)) then " +
//                        "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
//                        "redis.call('pexpire', KEYS[1], ARGV[1]); " +
//                        "return nil; " +
//                        "end; " +
//                        "return redis.call('pttl', KEYS[1]);",
//                Collections.singletonList(getRawName()), unit.toMillis(leaseTime), getLockName(threadId));


        RedissonClient client = Redisson.create();
        RLock lock = client.getLock("payLock");
        // 不设置过期时间，默认三十秒
        if (!lock.tryLock()) {
            System.out.println("未获取到锁");
            return;
        }
        try {
            System.out.println("支付操作");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }

    static void tryLockTime() throws InterruptedException {
        RedissonClient client = Redisson.create();
        RLock lock = client.getLock("orderLock");
        // waitTime设置的时间是等待时间，没有获取到锁，最大尝试等10s，leaseTime设置是过期时间
        if (!lock.tryLock(10, 10, TimeUnit.SECONDS)) {
            return;
        }
        try {
            System.out.println("支付操作");
            Thread.sleep(2000);
        } finally {
            ;
            lock.unlock();
        }
    }


    static void lock() {
        RedissonClient client = Redisson.create();
        RLock lock = client.getLock("orderLock");
        //leaseTime设置是过期时间
        lock.lock(3, TimeUnit.SECONDS);
        try {
            System.out.println("支付操作");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}