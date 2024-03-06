package org.javaboy.nacos.service;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;

/**
 * @author majin.wj
 * @date 2023/6/17 8:25 PM
 * @desc
 */
public class SubscribeDemo {
    public static void main(String[] args) throws NacosException {
        NamingService namingService = NacosFactory.createNamingService("127.0.0.1:8848");
        namingService.subscribe("sz", new EventListener() {
            @Override
            public void onEvent(Event event) {

            }
        });

    }
}
