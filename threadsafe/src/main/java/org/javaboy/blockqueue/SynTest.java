package org.javaboy.blockqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue 是这样一种阻塞队列，其中每个 put 必须等待一个 take，反之亦然。
 */
public class SynTest {

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        queue.take();
    }


    /**
     * 当生成的数据没有被消费是，会被阻塞
     *
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            String take = null;
            try {
                Thread.sleep(2000);
                take = queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("消费：" + take);
        }).start();
        // 消费数据后，返回
        queue.put("zs");
        System.out.println("end");
    }

    static void test2() throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            String poll = null;
            try {
                poll = queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("take:" + poll);
        }).start();
        Thread.sleep(100);
        queue.add("zs");
        System.out.println("end");
    }

    static void demo() throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            while (true) {
                try {
                    String data = queue.take();
                    System.out.println("从队列取数据:" + data);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        // 必须先有线程，在等待数据。
        Thread.sleep(100);
        boolean offer = queue.offer("zs");
        System.out.println(offer);
    }
}
