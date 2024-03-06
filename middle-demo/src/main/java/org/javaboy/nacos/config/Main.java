package org.javaboy.nacos.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @author majin.wj
 * @date 2023/6/15 4:49 PM
 * @desc
 */
public class Main {
    public static void main(String[] args) throws NacosException, InterruptedException {
        ConfigService configService = NacosFactory.createConfigService("localhost:8848");

        String config = configService.getConfig("datasource.properties", "if-landlord", 3000);

        System.out.println(config);


        boolean published = configService.publishConfig("datasource.properties", "if-landlord", "helloworld");
        System.out.println(published);
        configService.addListener("datasource.properties", "if-landlord", new Listener() {
            @Override
            public Executor getExecutor() {
                System.out.println("executor...");
                return null;
            }
            // 最新的配置变更。
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("收到了配置信息:" + configInfo);
            }
        });

        new CountDownLatch(1).await();


    }
}