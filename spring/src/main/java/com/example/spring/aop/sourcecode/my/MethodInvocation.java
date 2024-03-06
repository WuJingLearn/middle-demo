package com.example.spring.aop.sourcecode.my;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 8:11 PM
 * @desc
 */
public class MethodInvocation {

    private Object target;
    private Method method;
    private Object[] args;

    public MethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
        method.setAccessible(true);
    }


    public Object invokeTargetMethod() {
        try {
            return method.invoke(target, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
