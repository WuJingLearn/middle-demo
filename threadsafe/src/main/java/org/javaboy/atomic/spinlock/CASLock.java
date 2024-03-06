package org.javaboy.atomic.spinlock;

import java.util.concurrent.atomic.AtomicReference;

class CASLock {

        private volatile AtomicReference<Thread> ownerThread = new AtomicReference<>();
        public void lock() {
            while (!ownerThread.compareAndSet(null, Thread.currentThread())) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread() + "get lock success");
        }

        public void unlock() {
            if (ownerThread.get() == Thread.currentThread()) {
                ownerThread.set(null);
                System.out.println(Thread.currentThread() + "release lock success");
            }
        }

    }