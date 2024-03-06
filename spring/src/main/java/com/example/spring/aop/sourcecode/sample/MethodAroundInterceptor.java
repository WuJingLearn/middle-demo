package com.example.spring.aop.sourcecode.sample;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 4:55 PM
 * @desc
 */
public class MethodAroundInterceptor extends AbstractAdvice implements MethodInterceptor {

    public MethodAroundInterceptor(Method adviceMethod, Object aspectBean) {
        super(adviceMethod, aspectBean);
    }

    @Override
    public Object invoke(Invocation invocation) {
        return invokeAroundAdviceMethod(invocation);
    }
}
