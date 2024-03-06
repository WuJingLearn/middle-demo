package com.example.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @author majin.wj
 * @date 2023/6/28 2:18 PM
 * @desc
 */
@Component
public class OrderServiceImpl implements OrderService{


    @Override
    public void create() {
        System.out.println("创建订单");
    }

    @Override
    public void payed() {
        System.out.println("支付订单");
    }
}
