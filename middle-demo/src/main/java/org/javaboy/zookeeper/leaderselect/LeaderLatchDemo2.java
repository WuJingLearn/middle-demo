package org.javaboy.zookeeper.leaderselect;

import org.apache.curator.framework.recipes.leader.LeaderLatch;

/**
 * @author majin.wj
 * @date 2023/5/27 4:11 PM
 * @desc
 */
public class LeaderLatchDemo2 {

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try {
                LeaderLatch latch = new LeaderLatch(null, "/leaderLatch");
                /**
                 *  内部异步的进行分布式锁抢占过程，最小的的节点成为leader节点，并
                 *  将hasLeaderShip变量设置为true. 由于是异步的进行分布式锁抢占，
                 *  start方法直接返回。 通过latch.hasLeaderShip方法判断是否是leader节点。
                 *  LeaderLatch.close方法，将当前的子节点删除掉即可，其他的线程就可以成为新的主节点了
                 *
                 */
                latch.start();
                // 阻塞直到成为leader节点
                latch.await();
                System.out.println(Thread.currentThread().getName()+"成为leader"+latch.hasLeadership());
                Thread.sleep(2000);
                latch.close();
            } catch (Exception e) {

            }


        }, "thread1").start();

        new Thread(() -> {
            try {
                LeaderLatch latch = new LeaderLatch(null, "/leaderLatch");
                /**
                 *  内部异步的进行分布式锁抢占过程，最小的的节点成为leader节点，并
                 *  将hasLeaderShip变量设置为true.成
                 *
                 */
                latch.start();

                latch.await();
                System.out.println(Thread.currentThread().getName()+"成为leader"+latch.hasLeadership());
                Thread.sleep(2000);
                latch.close();


            } catch (Exception e) {

            }
        }, "thread2").start();


    }

}
