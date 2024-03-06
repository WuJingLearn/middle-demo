package org.javaboy.zookeeper.distribute;

import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;

/**
 * @author majin.wj
 * @date 2023/5/27 2:35 PM
 * @desc
 */
public class DistributeSemaphore {

    public static void main(String[] args) throws Exception {

        /**
         * 在InterProcessSemaphoreV2构造方法方法中，会先构建一个分布式锁。
         * 在进行acquire时,先进行上锁操作，因为后面的逻辑需要保证原子的。
         * 1.获取锁之后，在lease目录下创建临时顺序节点。然后查询当前子节点数量，如果子节点数据小于maxLease的数。则返回，然后释放分布式锁。
         * 2.如果子节点数量超过最大release.则需要进行wait等待。并监听lease目录。当其他线程释放release时，则等待的线程唤醒，然后重新进行判断
         * 即可。
         *
         *
         */
        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(ZkClients.client(), "/sem", 10);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Lease lease = null;
                try {
                    lease = semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + lease);
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (lease != null) {
                        semaphore.returnLease(lease);
                    }
                }


            }).start();

        }
    }
}
