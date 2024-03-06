package com.example.spring.aop.sourcecode.my;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 8:11 PM
 * @desc
 */
public class BeforeInterceptor extends AbstractAdvice implements MethodInterceptor {


    MethodInterceptor next;

    public BeforeInterceptor(MethodInterceptor next, Object aspectBean, Method method) {
        super(method, aspectBean);
        this.next = next;
    }

    @Override
    public Object invoke(MethodInvocation invocation) {
        invokeAdviceMethod();
        return next.invoke(invocation);
    }
}
