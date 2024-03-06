package com.example.spring.aop.sourcecode.sample;

/**
 * @author majin.wj
 * @date 2023/6/28 4:42 PM
 * @desc
 */
public interface MethodInterceptor {

    Object invoke(Invocation invocation);

}
