package org.javaboy.lock.reentrantlock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    static int capacity = 10;

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            ConsumerTask consumerTask = new ConsumerTask();
            while (true) {
                consumerTask.run();
            }
        }, "consumerThread").start();

        Thread.sleep(100);

        new Thread(() -> {
            ProducerTask producerTask = new ProducerTask();
            while (true) {
                producerTask.run();
            }
        }, "producerThread").start();

    }

    static class ConsumerTask implements Runnable {
        @Override
        public void run() {

            try {
                lock.lock();
                while (queue.size() <= 0) {
                    System.out.println("没有苹果");
                    condition.await();
                }
                String data = queue.poll();
                System.out.println("消费数据:" + data);
                condition.signalAll();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ProducerTask implements Runnable {
        int i = 0;

        @Override
        public void run() {
            try {
                lock.lock();
                while (queue.size() >= capacity) {
                    condition.await();
                }
                System.out.println("放入苹果：" + i);
                queue.put("苹果" + i++);
                condition.signal();
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }

        }
    }
}
