package org.javaboy.adapter.handleradapter;

public interface HandlerAdapter {

    boolean supports(Object handler);


    /**
     * 在spring mvc中，handler有很多种形式，常用的是HandlerMethod，也就是通过@RequestMaping的方式。
     * 还有其他的形式，比如说实现Controller接口的方式
     *
     * 不同的适配器来执行方法。在不同的适配器中，将handler转为具体的Handler处理器类来执行。比如说HandlerMethod
     * @param request
     * @param handler
     * @return
     */
    Object handle(Object request, Object handler);


}
