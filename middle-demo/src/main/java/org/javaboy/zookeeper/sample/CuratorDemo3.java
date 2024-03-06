package org.javaboy.zookeeper.sample;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;

import java.util.List;

/**
 * @author majin.wj
 * @date 2023/5/21 5:00 PM
 * @desc
 */
public class CuratorDemo3 {

    public static void main(String[] args) throws Exception {

        String zkAddress = "127.0.0.1:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();





        client.getChildren().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent watchedEvent) throws Exception {


                List<String> child = client.getChildren().usingWatcher(this).forPath("/hello");
                System.out.println("孩子节点:"+child);

            }
        }).forPath("/hello");

        if(client.checkExists().forPath("/hello")!=null) {
            client.delete().deletingChildrenIfNeeded().forPath("/hello");
        }

        client.create().forPath("/hello");

        client.create().forPath("/hello/aa");
        client.create().forPath("/hello/bb");



    }
}
