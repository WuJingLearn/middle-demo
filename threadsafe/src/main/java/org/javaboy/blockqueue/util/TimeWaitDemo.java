package org.javaboy.blockqueue.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TimeWaitDemo {

    int count;
    ReentrantLock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();

    /**
     *  while(条件不满足){
     *      if(awaitTime<=0){
     *          return null;
     *      }
     *     awaitTime = await(awaitTime);
     *  }
     *
     *
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                if (nanos <= 0)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

}
