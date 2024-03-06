package org.javaboy.adapter.handleradapter.support;

import org.javaboy.adapter.handleradapter.HandlerAdapter;

public class RequestMappingHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerMethod;
    }

    @Override
    public Object handle(Object request, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        return null;
    }
}
