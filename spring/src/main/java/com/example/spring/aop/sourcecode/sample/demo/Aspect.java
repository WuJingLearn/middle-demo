package com.example.spring.aop.sourcecode.sample.demo;

import com.example.spring.aop.sourcecode.sample.ProceedingJoinPoint;

/**
 * @author majin.wj
 * @date 2023/6/28 5:17 PM
 * @desc
 */
public class Aspect {


    public void before() {
        System.out.println("前置通知");
    }

    public void after() {
        System.out.println("执行完成无论如何都通知");
    }

    public void afterReturn() {
        System.out.println("执行成功后通知");
    }

    public void afterThrow() {
        System.out.println("异常后通知");
    }

    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("环绕前");
            Object result = joinPoint.proceed();
            System.out.println("环绕成功后");
            return result;
        } catch (Exception e) {
            System.out.println("环绕失败后");
            return null;
        } finally {
            System.out.println("环绕无论如何");
        }
    }
}
