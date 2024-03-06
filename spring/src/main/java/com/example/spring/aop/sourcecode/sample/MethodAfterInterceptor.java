package com.example.spring.aop.sourcecode.sample;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 5:01 PM
 * @desc
 */
public class MethodAfterInterceptor extends AbstractAdvice implements MethodInterceptor{

    public MethodAfterInterceptor(Method adviceMethod, Object aspectBean) {
        super(adviceMethod, aspectBean);
    }

    @Override
    public Object invoke(Invocation invocation) {
        try {
            return invocation.proceed();
        }finally {
            invokeAdviceMethod();
        }

    }
}
