package org.javaboy.zookeeper.sample;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;

/**
 * @author majin.wj
 * @date 2023/5/21 5:00 PM
 * @desc
 */
public class CuratorDemo {

    public static void main(String[] args) throws Exception {


        for (int i = 0; i < 1; i++) {
            String zkAddress = "127.0.0.1:2181";
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
            client.start();
            byte[] bytes = client.getData().forPath("/user");
            System.out.println(bytes);

            String path = client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/user1/", "zs".getBytes());
            System.out.println(path);
        }





        new CountDownLatch(1).await();

//        Stat stat = client.checkExists().forPath("/user");
//        System.out.println(stat);
//
//        byte[] bytes = client.getData().forPath("/user");
//        System.out.println(new String(bytes));
//
//        stat = client.setData().forPath("/user", "ls".getBytes());
//        byte[] data = client.getData().forPath("/user");
//        System.out.println(new String(data));
//
//        // 在/user节点下创建临时顺序节点
//        for (int i = 0; i < 3; i++) {
//            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/user/child-");
//        }
//        // [child-0000000002, child-0000000001, child-0000000000]
//        List<String> children = client.getChildren().forPath("/user");
//        System.out.println(children);
//
//        // 删除节点 已经它的子节点
//        client.delete().deletingChildrenIfNeeded().forPath("/user");


    }
}
