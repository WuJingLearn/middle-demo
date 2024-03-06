package org.javaboy.zookeeper.leaderselect;

import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

/**
 * @author majin.wj
 * @date 2023/5/27 4:11 PM
 * @desc
 */
public class LeaderLatchDemo {

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try {
                LeaderLatch latch = new LeaderLatch(null, "/leaderLatch");
                latch.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {

                    }

                    @Override
                    public void notLeader() {

                    }
                });
                /**
                 *  内部异步的进行分布式锁抢占过程，最小的的节点成为leader节点，并
                 *  将hasLeaderShip变量设置为true. 由于是异步的进行分布式锁抢占，
                 *  start方法直接返回。 通过latch.hasLeaderShip方法判断是否是leader节点。
                 *  LeaderLatch.close方法，将当前的子节点删除掉即可，其他的线程就可以成为新的主节点了
                 *
                 *  checkLeadership 核心方法
                 *
                 */
                latch.start();

                while (!latch.hasLeadership()) {
                    System.out.println(Thread.currentThread().getName() + "参与选举中");
                    Thread.sleep(1000);
                }
                System.out.println(Thread.currentThread().getName() + "选举成功" + latch.hasLeadership());
                Thread.sleep(5000);
                latch.close();
                System.out.println(Thread.currentThread().getName() + "释放主节点" + latch.hasLeadership());


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

                while (!latch.hasLeadership()) {
                    System.out.println(Thread.currentThread().getName() + "参与选举中");
                    Thread.sleep(1000);
                }
                System.out.println(Thread.currentThread().getName() + "选举成功" + latch.hasLeadership());
                Thread.sleep(5000);
                latch.close();
                System.out.println(Thread.currentThread().getName() + "释放主节点" + latch.hasLeadership());

            } catch (Exception e) {

            }
        }, "thread2").start();


    }

}
