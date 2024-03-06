package com.example.spring.aop.sourcecode.sample;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 4:57 PM
 * @desc
 */
public class ProceedingJoinPoint implements JoinPoint{

    private Invocation invocation;

    public ProceedingJoinPoint(Invocation invocation){
        this.invocation = invocation;
    }

    @Override
    public Object[] arguments() {
        return new Object[0];
    }

    @Override
    public Object returnValue() {
        return null;
    }

    @Override
    public Method method() {
        return null;
    }

    public Object proceed(){
       return  invocation.proceed();
    }
}
