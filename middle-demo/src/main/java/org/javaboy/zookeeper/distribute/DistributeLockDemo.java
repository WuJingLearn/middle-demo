package org.javaboy.zookeeper.distribute;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @author majin.wj
 * @date 2023/5/26 9:33 PM
 * @desc
 */
public class DistributeLockDemo {


    /**
     *  分布式锁：就是同一时刻，只能有同一个进程的一个线程来访问共享资源。
     *  在zookeeper中，是通过临时顺序节点+watcher机制来实现。
     *  1.首先构建一个分布式锁，需要在zk中创建一个持久节点，因为只有持久节点才能有子节点。
     *  2.多个线程同时抢占锁的过程，就是同时向这个持久节点创建临时顺序子节点。创建子节点序号最小的那个线程
     *  会获得锁，然后其他的线程监听序号上一个序号的节点的变更事件，然后本地wait等待。当释放锁的时候，监听这个节点的线程就会收到通知,然后通过notify唤醒
     *  这个线程。

     *
     * @param args
     */

    public static void main(String[] args) throws Exception {


        InterProcessMutex interProcessMutex = new InterProcessMutex(ZkClients.client(), "/test-1");
        interProcessMutex.acquire();


        Thread.sleep(100000);
        interProcessMutex.release();

    }


}
