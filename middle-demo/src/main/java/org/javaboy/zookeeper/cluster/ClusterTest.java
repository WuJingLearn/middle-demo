package org.javaboy.zookeeper.cluster;

import org.apache.curator.framework.CuratorFramework;

public class ClusterTest {

    public static void main(String[] args) throws Exception {


        CuratorFramework client = ZkSlaveClient.client();

//
//        String stat = client.create().forPath("/clusterTest", "hello".getBytes());
//        System.out.println(stat);
        byte[] bytes = client.getData().forPath("/clusterTest");
        System.out.println("get data " + new String(bytes));


    }
}
