package com.example.spring.aop.sourcecode.my;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 8:11 PM
 * @desc
 */
public class AfterInterceptor extends AbstractAdvice implements MethodInterceptor {


    MethodInterceptor next;

    public AfterInterceptor(MethodInterceptor next, Object aspectBean, Method method) {
        super(method, aspectBean);
        this.next = next;
    }

    @Override
    public Object invoke(MethodInvocation invocation) {
        try {
            return next.invoke(invocation);
        }finally {
            invokeAdviceMethod();
        }
    }
}
