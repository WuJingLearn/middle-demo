package org.javaboy.atomic.spinlock;

import java.util.concurrent.atomic.AtomicReference;

public class Lock {


    private AtomicReference<Thread> reference = new AtomicReference<>();
    private int state = 0;

    void lock() {
        Thread current = Thread.currentThread();
        if (reference.get() == current) {
            state++;
            return;
        }
        while (!reference.compareAndSet(null, current)) {

        }
        state++;
    }

    void unlock() {
        Thread current = Thread.currentThread();
        if (current == reference.get()) {
            state--;
            if (state == 0) {
                reference.compareAndSet(current, null);
            }
        }
    }


    public static void main(String[] args) {
        Lock spinLock = new Lock();
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
