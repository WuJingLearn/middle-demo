package org.javaboy.thread;

/**
 * @author majin.wj
 * @date 2023/6/25 8:09 PM
 * @desc
 */
public class ThreadDemo {

    public static void main(String[] args) {


        new Thread(()->{

            Object o = new Object();
            synchronized (o) {
                try {
                    // java.lang.Thread.State: WAITING (on object monitor)
                    o.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        },"thread1").start();


        new Thread(()->{

            try {
                //TIMED_WAITING (sleeping)

                Thread.sleep(100000000);
            } catch (InterruptedException e) {


            }

        },"thread2").start();

        new Thread(()->{

            Object o = new Object();
            synchronized (o){
                try {
                    //TIMED_WAITING (on object monitor)
                    o.wait(100000000);
                } catch (InterruptedException e) {


                }
            }


        },"thread3").start();


    }


}
