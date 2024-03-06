package org.javaboy.strategy.handlemappiing;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式有两种方式： 使用链表或者数组来保存处理器。
 * 这里handlerMappings通过数组的方式来保存所有的处理器。即使这里没有使用一个HandlerMappingChain
 * 来进行封装，这很明显也是一个责任链。
 * 同时每种HandlerMapping负责一种请求方式，又是策略模式的实现；策略模式通常和责任链等模式结合使用
 *
 * 策略模式的步骤：
 * 1.获取具体的策略实现
 * 2.具体的策略类调用
 */
public class DispatcherServlet {

    private List<HandlerMapping> handlerMappings = new ArrayList<>();


    /**
     * 这里的设计模式，可以说是责任链，也可以说是策略模式。
     *
     * @param request
     * @return
     */
    public HandlerExecutionChain getHandler(Object request) {
        if (handlerMappings != null) {
            for (HandlerMapping handlerMapping : handlerMappings) {
                HandlerExecutionChain handler = handlerMapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }

}
