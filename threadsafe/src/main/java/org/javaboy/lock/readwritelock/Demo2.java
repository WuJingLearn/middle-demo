package org.javaboy.lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 获取读锁判断顺序：
 * 1.先判断有没有写锁，或者写锁自己的线程
 * 2.之前有写锁等待了。
 *
 *
 *
 *
 *
 *
 */
public class Demo2 {


    /***
     *
     *
     *
     *  static final class ThreadLocalHoldCounter
     *             extends ThreadLocal<HoldCounter> {
     *             public HoldCounter initialValue() {
     *                 return new HoldCounter();
     *             }
     *         }
     *
     *  private transient ThreadLocalHoldCounter readHolds;
     *
     *           rh = readHolds.get();
     *
     *   读锁是线程共享的，读锁也支持重入，读锁的释放的原理是怎样的呢
     *   通过一个ThreadLocalHoldCounter,这是一个ThreadLocal类型，用来记录一个线程，读锁的重入次数。
     *
     * @param args
     */
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws Exception {
//        demo1();
//        demoUnlock();
        demo3();
    }


    /**
     * 上写锁的过程：
     * 1.state不等于0,w=0,表示当前已经有线程上了读锁。上了读锁，则写锁不可以上
     * 没上写锁，但是有人上了读锁，此时不可以在上写锁。
     * 2.没线程上读锁，但是其他线程上了写锁。则当前线程写锁也不可以上。
     *
     * 3.如果是一个线程，写读写，能上
     * 3。state=0 当前没有线程获取锁，则上写锁
     * <p>
     * protected final boolean tryAcquire(int acquires) {
     * Thread current = Thread.currentThread();
     * int c = getState();
     * int w = exclusiveCount(c);
     * if (c != 0) {
     * // (Note: if c != 0 and w == 0 then shared count != 0)
     * if (w == 0 || current != getExclusiveOwnerThread())
     * return false;
     * if (w + exclusiveCount(acquires) > MAX_COUNT)
     * throw new Error("Maximum lock count exceeded");
     * // Reentrant acquire
     * setState(c + acquires);
     * return true;
     * }
     * if (writerShouldBlock() ||
     * !compareAndSetState(c, c + acquires))
     * return false;
     * setExclusiveOwnerThread(current);
     * return true;
     * }
     */
    static void testWrite() {
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        writeLock.unlock();
    }

    /**
     * 读锁的获取：
     * 1.当前有线程获取了写锁，但不是自己的线程，无法获取读锁
     * 2.没有线程获取写锁，那么此时不是说一定会上的了读锁，首先需要看一下阻塞队列中，
     * 如果阻塞队列中有元素，说明这个阻塞队列中有线程在等待，读锁是共享的，阻塞的节点就会是写节点了
     * 如果此刻上读锁的时候，已经有写锁在等待了，此时读锁应该不获取。为了防止写锁饥饿
     * <p>
     * <p>
     * protected final int tryAcquireShared(int unused) {
     * Thread current = Thread.currentThread();
     * int c = getState();
     * if (exclusiveCount(c) != 0 &&
     * getExclusiveOwnerThread() != current)
     * return -1;
     * int r = sharedCount(c);
     * if (!readerShouldBlock() &&
     * r < MAX_COUNT &&
     * compareAndSetState(c, c + SHARED_UNIT)) {
     * if (r == 0) {
     * firstReader = current;
     * firstReaderHoldCount = 1;
     * } else if (firstReader == current) {
     * firstReaderHoldCount++;
     * } else {
     * ReentrantReadWriteLock.Sync.HoldCounter rh = cachedHoldCounter;
     * if (rh == null || rh.tid != getThreadId(current))
     * cachedHoldCounter = rh = readHolds.get();
     * else if (rh.count == 0)
     * readHolds.set(rh);
     * rh.count++;
     * }
     * return 1;
     * }
     * return fullTryAcquireShared(current);
     * }
     */
    static void testRead() throws InterruptedException {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        System.out.println("开始读锁");
        Thread.sleep(1000000);
        System.out.println("结束读锁");
        readLock.unlock();
    }


    /**
     *  模拟读 写 读的场景。
     *  在第二次上读锁的时候，发现阻塞队列中已经有
     * @throws InterruptedException
     */
    static void demo1() throws InterruptedException {

        new Thread(() -> {
            try {
                testRead();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                testWrite();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                testRead();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }).start();
    }


    /**
     * 释放锁逻辑
     * 写锁在释放的时候，会将第一个阻塞队列中的线程唤醒，这里是第一个读线程，
     * 第一个读线程唤醒之后，会进行for循环执行逻辑，然后会唤醒第二个读线程。
     * 里面逻辑太复杂了。不不需要完全知道
     *
     */
    static void demoUnlock() throws InterruptedException {
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

        new Thread(()->{
            try {
                Thread.sleep(500);
                testRead();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"read-1").start();

        new Thread(()->{
            try {
                Thread.sleep(1000);

                testRead();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"read-2").start();

        writeLock.lock();
        System.out.println("写锁start");
        Thread.sleep(1000);
        System.out.println("写锁end");

        writeLock.unlock();

    }

    /**
     * 上写锁可以理解为：
     * 1，之前当前线程有没有上过，上过则重入
     * 2. 以前没写锁，但是有人上了读锁，则不能上
     * 3。其他线程上了写锁，则不能上
     */

    static void demo3(){
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        writeLock.lock();;
        readLock.lock();;
        writeLock.lock();;
        // 逻辑继续执行，即使上了读锁，因为之前上过一次写锁
        System.out.println("执行逻辑");

    }




}
