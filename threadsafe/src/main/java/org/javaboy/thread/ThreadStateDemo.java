package org.javaboy.thread;

/**
 * @author majin.wj
 * @date 2023/6/26 1:35 PM
 * @desc
 */
public class ThreadStateDemo {

    public static void main(String[] args) {

        Object lock = new Object();
        for (int i = 0; i < 10; i++) {

            new Thread(()->{

                synchronized (lock) {

                    try {
                        Thread.sleep(100000000);
                    } catch (InterruptedException e) {
                        
                    }

                }

            }).start();

        }
    }
}
