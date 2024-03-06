package org.javaboy.blockqueue;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 无界的，数据存储，put时会扩容
         * ReentrantLock + notEmpty;
         */
        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>(10);

        queue.put("zs");
        queue.put("ls");


        String take = queue.take();

    }
}
