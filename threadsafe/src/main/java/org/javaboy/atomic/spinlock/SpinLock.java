package org.javaboy.atomic.spinlock;

import java.util.concurrent.atomic.AtomicLong;

public class SpinLock {

    private AtomicLong state = new AtomicLong(0);

    public void lock() {
        while (!state.compareAndSet(0, 1)) {
        }


    }

    public void unlock() {
        state.compareAndSet(1, 0);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.lock();
            System.out.println("thread1 begin");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("thread1 end");
            spinLock.unlock();

        }).start();

        new Thread(() -> {
            spinLock.lock();
            System.out.println("thread2 begin");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("thread2 end");
            spinLock.unlock();
        }).start();


    }

}
