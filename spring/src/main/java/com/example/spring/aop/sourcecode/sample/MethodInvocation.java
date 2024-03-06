package com.example.spring.aop.sourcecode.sample;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/28 4:42 PM
 * @desc
 */
public class MethodInvocation implements Invocation {


    List<MethodInterceptor> interceptorList = new ArrayList<>();

    private Method method;
    private Object target;
    private Object[] args;

    int i = -1;

    public MethodInvocation(Method method, Object target, Object[] args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }


    @Override
    public Object proceed() {
        if (i == interceptorList.size() - 1) {
            return invokeTargetMethod();
        }
        MethodInterceptor methodInterceptor = interceptorList.get(++i);
        return methodInterceptor.invoke(this);
    }


    Object invokeTargetMethod() {
        try {
            return method.invoke(target, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 需要控制添加顺序。
     *
     * @param interceptor
     */
    public void addInterceptor(MethodInterceptor interceptor) {
        interceptorList.add(interceptor);
    }
}
