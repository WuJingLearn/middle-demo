package com.example.spring.aop.sourcecode.my;

/**
 * @author majin.wj
 * @date 2023/6/28 8:21 PM
 * @desc
 */
public class ReflectMethodInterceptor implements MethodInterceptor{
    @Override
    public Object invoke(MethodInvocation invocation) {
        return invocation.invokeTargetMethod();
    }
}
