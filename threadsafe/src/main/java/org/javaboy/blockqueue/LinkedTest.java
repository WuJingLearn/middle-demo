package org.javaboy.blockqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedTest {

    static void see() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        try {
            // putLock
            queue.put("data");

            String data = queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {


    }
}
