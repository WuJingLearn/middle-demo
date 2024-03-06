package org.javaboy.zookeeper.distribute;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author majin.wj
 * @date 2023/5/26 9:33 PM
 * @desc
 */
public class ZkClients {


    public static CuratorFramework client() {
        String zkAddress = "127.0.0.1:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();
        return client;
    }

    public static void main(String[] args) throws Exception {
        String path = "/aa/lock-";
        String ourPath = client().create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        System.out.println(ourPath);

        ourPath = client().create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        System.out.println(ourPath);

        path = "/aa/lock2-";
        ourPath = client().create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        System.out.println(ourPath);

        path = "/aa/lock2-/a";
        ourPath = client().create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        System.out.println(ourPath);

    }

}
