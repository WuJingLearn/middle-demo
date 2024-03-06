package com.example.spring.aop.sourcecode.my;

import com.example.spring.aop.sourcecode.sample.Invocation;
import com.example.spring.aop.sourcecode.sample.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 4:47 PM
 * @desc
 */
public abstract class AbstractAdvice {

    private Method adviceMethod;

    private Object aspectBean;


    public AbstractAdvice(Method adviceMethod, Object aspectBean) {
        this.adviceMethod = adviceMethod;
        this.aspectBean = aspectBean;
        adviceMethod.setAccessible(true);
    }

    public Object invokeAdviceMethod() {

        try {
            return adviceMethod.invoke(aspectBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Object invokeAroundAdviceMethod(Invocation invocation) {

        try {
            // 环绕通知，需要自己控制执行流程。在环绕通知代码中，通过joinPint.proceed()执行
            ProceedingJoinPoint proceedingJoinPoint = new ProceedingJoinPoint(invocation);
            return adviceMethod.invoke(aspectBean, proceedingJoinPoint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
