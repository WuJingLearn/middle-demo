package org.javaboy.zookeeper.cluster;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author majin.wj
 * @date 2023/5/26 9:33 PM
 * @desc
 */
public class ZkSlaveClient {


    public static CuratorFramework client() {
        String zkAddress = "127.0.0.1:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();
        return client;
    }


}
