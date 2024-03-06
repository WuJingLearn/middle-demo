package org.javaboy.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ArrayBlockQueuing还是很好理解的， 读写使用了一个lock。不能同时并发读写。
 * LinkedBlocking：读和写分别使用了两把锁;
 *
 * 阻塞队列都是通过ReentrantLock+Condition来实现
 * 非阻塞队列是通过cas+不断重试来实现
 */
public class ArrayTest {

    static void see() throws InterruptedException {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1);
        queue.put(null);
        queue.take();
    }
    public static void main(String[] args) throws InterruptedException {


        ArrayQueue queue = new ArrayQueue(2);
        queue.put("zs");
        queue.put("ls");

        System.out.println(queue.take());
        System.out.println(queue.take());
        queue.put("ww");
    }




    static class ArrayQueue {

        private Object[] items;
        private int capacity;
        private int size;

        private int putIndex;
        private int takeIndex;

        private ReentrantLock lock = new ReentrantLock();

        Condition notFull = lock.newCondition();
        Condition notEmpty = lock.newCondition();


        public ArrayQueue(int capacity) {
            this.capacity = capacity;
            this.items = new Object[capacity];
        }

        public void put(Object item) throws InterruptedException {
            lock.lock();
            try {
                while (size == capacity) {
                    /**
                     * 写数据，等待容器有容量继续写，所以命名是不满notFull
                     */
                    notFull.await();
                }
                if (putIndex == capacity) {
                    putIndex = 0;
                }
                items[putIndex++] = item;
                size++;
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        public Object take() throws InterruptedException {
            lock.lock();
            try {
                while (size == 0) {
                    /**
                     * 消费数据，等待有元素可以消费，所以命名是不null，notEmpty
                     */
                    notEmpty.await();
                }
                if (takeIndex == capacity) {
                    takeIndex = 0;
                }
                Object item = items[takeIndex];
                items[takeIndex++] = null;
                size--;
                notFull.signal();
                return item;
            } finally {
                lock.unlock();
            }
        }
    }
}
