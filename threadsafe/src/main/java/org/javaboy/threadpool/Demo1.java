package org.javaboy.threadpool;


import java.util.concurrent.*;

/**
 * 当先活跃线程数
 * 队列积累任务
 * 最大线程数
 *
 * 当出现当前活跃线程数  == 最大线程数，并且队列中也积累了很对任务，此时可以将最大线程调大一些。
 * 1.考虑是不是任务执行的太慢了，占用了过多
 * 2.适当增大一些线程数
 *
 */
public class Demo1 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS, new SynchronousQueue<>());

        for (int i = 0; i < 2; i++) {
            executor.execute(()->{
                System.out.println("task executer");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        // 活跃的线程。正在处理任务的数量
        // 当前activeCount == max 表示线程池已经打满
        int activeCount = executor.getActiveCount();

        // 线程池中创建过的最大线程
        int largestPoolSize = executor.getLargestPoolSize();

        // 当前线程池中线程数 当前线程数
        int workerSize = executor.getPoolSize();

        // 积累任务数量
        int taskSize = executor.getQueue().size();

        executor.getRejectedExecutionHandler();

    }
}
