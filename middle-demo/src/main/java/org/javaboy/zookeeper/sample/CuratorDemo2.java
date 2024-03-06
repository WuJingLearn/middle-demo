package org.javaboy.zookeeper.sample;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author majin.wj
 * @date 2023/5/21 5:00 PM
 * @desc
 */
public class CuratorDemo2 {

    public static void main(String[] args) throws Exception {

        String zkAddress = "127.0.0.1:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();

        byte[] data = client.getData().forPath("/dubbo/org.apache.dubbo.demo.DemoService/providers");
        System.out.println(new String(data));


        if (client.checkExists().forPath("/testData") != null) {
            client.delete().forPath("/testData");
        } else {
            client.create().forPath("/testData");
            byte[] bytes = client.getData().forPath("/testData");
            System.out.println(new String(bytes));
        }


    }
}
