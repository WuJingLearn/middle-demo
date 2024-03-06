package org.javaboy.nacos.service;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author majin.wj
 * @date 2023/6/17 2:27 PM
 * @desc
 *
 *
 * nacos1.x注册服务使用的是http的方式和server通信
 * nacos2.x，注册临时服务使用的是grpc,使用持久化的还是使用http方式。
 * 持久化实列不需要心跳。持久化实列不是做为我们rpc服务注册中心使用的，而是在其他应用场景。
 *
 *
 *
 */
public class RegisterDemo {

    public static void main(String[] args) throws NacosException, InterruptedException {
        NamingService namingService = NacosFactory.createNamingService("127.0.0.1:8848");

        /**
         *  注册时，默认cluster:default,默认组：defalut_group
         *
         */
        namingService.registerInstance("org.javaboy.hello.service", "127.0.0.1", 8888);
        new CountDownLatch(1).await(10000, TimeUnit.SECONDS);


    }
}
