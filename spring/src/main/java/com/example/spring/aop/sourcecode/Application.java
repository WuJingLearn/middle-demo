package com.example.spring.aop.sourcecode;

import com.example.spring.aop.OrderService;
import com.example.spring.aop.OrderServiceImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author majin.wj
 * @date 2023/6/28 2:16 PM
 * @desc
 */
public class Application {


    public static void main(String[] args) {

        ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.setTarget(new OrderServiceImpl());
        proxyFactory.setInterfaces(OrderService.class);


        Object proxy = proxyFactory.getProxy();
        System.out.println(proxy);

        OrderService service = (OrderService) proxy;

        service.create();


        /**
         *
         *  ExposeInvocationInterceptor
         *
         *  Advisor中包含Advice
         *  AspectJAroundAdvice
         *  AspectJMethodBeforeAdvice   MethodBeforeAdviceAdapter
         *
         *  AfterReturningAdvice AfterReturningAdviceAdapter
         *
         *
         *
         */

    }


}
