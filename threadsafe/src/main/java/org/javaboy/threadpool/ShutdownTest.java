package org.javaboy.threadpool;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的关闭可以通过shutdown+awaitTermination实现优雅关闭
 */
public class ShutdownTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行任务");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        /**
         * 会执行完队列中所有的任务
         */
        executor.shutdown();

        List<Runnable> runnables = executor.shutdownNow();

        /**
         *         mainLock.lock();
         *         try {
         *             for (;;) {
         *                 if (runStateAtLeast(ctl.get(), TERMINATED))
         *                     return true;
         *                 if (nanos <= 0)
         *                     return false;
         *                 // 剩余的时间
         *                 nanos = termination.awaitNanos(nanos);
         *             }
         *         } finally {
         *             mainLock.unlock();
         *         }
         *
         *
         */
        boolean b = executor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println(b);
    }
}
