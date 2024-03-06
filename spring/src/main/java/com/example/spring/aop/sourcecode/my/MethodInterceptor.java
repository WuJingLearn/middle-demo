package com.example.spring.aop.sourcecode.my;

/**
 * @author majin.wj
 * @date 2023/6/28 8:10 PM
 * @desc
 */
public interface MethodInterceptor {


    Object invoke(MethodInvocation invocation);

}
