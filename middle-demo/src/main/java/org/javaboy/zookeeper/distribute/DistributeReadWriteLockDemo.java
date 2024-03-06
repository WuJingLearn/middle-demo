package org.javaboy.zookeeper.distribute;

import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

/**
 * @author majin.wj
 * @date 2023/5/27 10:58 AM
 * @desc
 */
public class DistributeReadWriteLockDemo {


    public static void main(String[] args) throws Exception {
        readWriteLock();
    }


    /**
     * https://zhuanlan.zhihu.com/p/444844432
     *
     * 写锁的路径是/path/write..什么的
     * 读锁的路径是/path/read..什么的， 通过判断节点名称中是否包含write来确认是否有写锁/
     *
     *读锁加锁过程：
     * 1.子节点包含写锁，当前节点的序号在写锁节点之前，则获取锁，否则获取锁失败
     * 2.自节点不包含写锁：当前节点在子节点的有序集合的index小与Integer.MAX 即可
     *
     * 写锁加锁：
     * 写锁加锁的逻辑直接是InterProcessMutex的逻辑，当前节点必须是所有子节点的有序集合的最小才可以。
     *
     *
     * 读锁和写锁的释放逻辑：
     * 锁的释放逻辑都是一样的，先根据当前线程从threadData中去数据，如果没有，表示当前线程没有获取锁，抛出异常。
     * 如果有数据，则对计数器-1.如果计数器=0则去删除对应的临时节点。
     *
     *
     * @throws Exception
     */

    public static void readWriteLock() throws Exception {

        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(ZkClients.client(), "/read-write");
        InterProcessReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.acquire();

        InterProcessReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.acquire();


        Thread.sleep(100000);
        readLock.acquire();

        writeLock.acquire();

        writeLock.release();

    }

}
