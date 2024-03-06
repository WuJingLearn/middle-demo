package org.javaboy.strategy.handlemappiing;

/**
 * spring mvc中 处理器映射接口，通过请求地址获取处理器
 */
public interface HandlerMapping {

    HandlerExecutionChain getHandler(Object requestURL);

}
