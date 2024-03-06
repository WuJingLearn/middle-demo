package com.example.spring.aop.sourcecode.sample;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 5:03 PM
 * @desc
 */
public class MethodAfterThrowingInterceptor extends AbstractAdvice implements MethodInterceptor {

    public MethodAfterThrowingInterceptor(Method adviceMethod, Object aspectBean) {
        super(adviceMethod, aspectBean);
    }

    @Override
    public Object invoke(Invocation invocation) {
        try {
            return invocation.proceed();
        } catch (Exception e) {
            // 异常时执行通知
            invokeAdviceMethod();
            throw e;
        }
    }
}
