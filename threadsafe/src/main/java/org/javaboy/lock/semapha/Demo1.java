package org.javaboy.lock.semapha;

import java.util.concurrent.Semaphore;

public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        /**
         * 通过 自旋的方式，修改state
         */
        semaphore.acquire(1);
    }
}
