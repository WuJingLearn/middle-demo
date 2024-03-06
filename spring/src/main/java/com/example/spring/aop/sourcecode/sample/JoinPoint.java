package com.example.spring.aop.sourcecode.sample;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 4:49 PM
 * @desc
 */
public interface JoinPoint {


    Object[] arguments();

    Object returnValue();

    Method method();

}
