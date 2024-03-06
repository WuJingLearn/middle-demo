package com.example.spring.aop.sourcecode.sample;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 4:44 PM
 * @desc
 */
public class MethodBeforeInterceptor extends AbstractAdvice implements MethodInterceptor {


    public MethodBeforeInterceptor(Method advicceMethod, Object aspectBean) {
        super(advicceMethod, aspectBean);
    }

    @Override
    public Object invoke(Invocation invocation) {
        // 前置通知先执行前置方法
        invokeAdviceMethod();
        return invocation.proceed();
    }
}
