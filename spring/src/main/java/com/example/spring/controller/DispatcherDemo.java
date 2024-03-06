package com.example.spring.controller;

public class DispatcherDemo {


//    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 1.根据请求路径，找到具体的处理器链，具体的处理器(HandlerMethod)和Interceptor封装在里面
//        HandlerExecutionChain mappedHandler = getHandler(processedRequest);
//        if (mappedHandler == null) {
//            return;
//        }
//        // 2.获得具体的适配器
//        HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
//        // 3.执行拦截器的前置方法，如果有一个责任链返回false则直接返回，不继续处理请求
//        if (!mappedHandler.applyPreHandle(processedRequest, response)) {
//            return;
//        }
//
//        // 3.通过适配器执行，本质上，还是通过处理器来执行
//        mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
//
//        // 4.执行拦截器的后置处理方法
//        mappedHandler.applyPostHandle(processedRequest, response, mv);
//
//        // 5.处理结果，响应请求
//        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
//
}