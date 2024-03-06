package org.javaboy.adapter.handleradapter.support;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class HandlerMethod {

    private Object bean;
    private Method method;

}
