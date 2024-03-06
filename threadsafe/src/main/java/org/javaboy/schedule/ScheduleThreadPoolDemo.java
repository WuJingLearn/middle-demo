package org.javaboy.schedule;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author majin.wj
 * @date 2023/6/19 3:12 PM
 * @desc 定时任务线程池，最多只能使用corePoolSize个线程来执行定时任务；
 * 在提交任务的时候，如果线程数量等于了核心线程，是不会继续创建的线程的，
 * 定时任务线程池的提交任务的执行逻辑：
 * 1.将任务放入延迟队列中
 * 2.如果当前线程数小于核心线程数，创建线程。
 */
public class ScheduleThreadPoolDemo {

    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    public static void main(String[] args) {
        test1();
    }

    static void test2() {

        for (int i = 0; i < 10; i++) {
            executor.schedule(() -> {
                System.out.println("start");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("end");
            }, 1000000, TimeUnit.SECONDS);

        }
    }

    static void test1() {

        // 固定频率，不受任务的执行时长影响。每间隔delay时间就执行一次
        //
        executor.scheduleAtFixedRate(() -> {

            System.out.println("当前任务开始时间" + new Date());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, 0, 3, TimeUnit.SECONDS);


        //任务执行完后，延迟固定时间后执行
        executor.scheduleWithFixedDelay(() -> {

            System.out.println("当前任务开始时间" + new Date());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, 0, 1, TimeUnit.SECONDS);

    }

}
