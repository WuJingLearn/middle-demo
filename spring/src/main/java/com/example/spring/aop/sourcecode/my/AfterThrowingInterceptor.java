package com.example.spring.aop.sourcecode.my;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 8:11 PM
 * @desc
 */
public class AfterThrowingInterceptor extends AbstractAdvice implements MethodInterceptor {


    MethodInterceptor next;

    public AfterThrowingInterceptor(MethodInterceptor next, Object aspectBean, Method method) {
        super(method, aspectBean);
        this.next = next;
    }

    @Override
    public Object invoke(MethodInvocation invocation) {
        try {
            return next.invoke(invocation);
        }catch (Exception e) {
            invokeAdviceMethod();
            throw e;
        }

    }
}
