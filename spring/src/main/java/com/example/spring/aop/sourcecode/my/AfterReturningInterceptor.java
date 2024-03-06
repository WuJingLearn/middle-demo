package com.example.spring.aop.sourcecode.my;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 8:11 PM
 * @desc
 */
public class AfterReturningInterceptor extends AbstractAdvice implements MethodInterceptor {


    MethodInterceptor next;

    public AfterReturningInterceptor(MethodInterceptor next, Object aspectBean, Method method) {
        super(method, aspectBean);
        this.next = next;
    }

    @Override
    public Object invoke(MethodInvocation invocation) {
        Object ret = next.invoke(invocation);
        // 执行增强
        invokeAdviceMethod();
        return ret;
    }
}
