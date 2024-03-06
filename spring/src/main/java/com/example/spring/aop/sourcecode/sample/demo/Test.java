package com.example.spring.aop.sourcecode.sample.demo;

import com.example.spring.aop.sourcecode.sample.*;

import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/28 5:17 PM
 * @desc
 */
public class Test {


    void hello() {
        System.out.println("hello方法哦");
        int i = 1 / 0;
    }

    public static void main(String[] args) throws Exception {
        Aspect aspect = new Aspect();
        Class<? extends Aspect> cls = aspect.getClass();
        Method before = cls.getDeclaredMethod("before");
        Method after = cls.getDeclaredMethod("after");
        Method afterReturn = cls.getDeclaredMethod("afterReturn");
        Method afterThrow = cls.getDeclaredMethod("afterThrow");
        Method around = cls.getDeclaredMethod("around", ProceedingJoinPoint.class);

        MethodBeforeInterceptor methodBeforeInterceptor = new MethodBeforeInterceptor(before, aspect);
        MethodAroundInterceptor methodAroundInterceptor = new MethodAroundInterceptor(around, aspect);
        MethodAfterReturningInterceptor methodAfterReturningInterceptor = new MethodAfterReturningInterceptor(afterReturn, aspect);
        MethodAfterThrowingInterceptor methodAfterThrowingInterceptor = new MethodAfterThrowingInterceptor(afterThrow, aspect);
        MethodAfterInterceptor methodAfterInterceptor = new MethodAfterInterceptor(after, aspect);

        Test test = new Test();
        Method method = test.getClass().getDeclaredMethod("hello");
        method.setAccessible(true);
        MethodInvocation invocation = new MethodInvocation(method, test, null);
        invocation.addInterceptor(methodAroundInterceptor);
        invocation.addInterceptor(methodBeforeInterceptor);
        invocation.addInterceptor(methodAfterInterceptor);
        invocation.addInterceptor(methodAfterThrowingInterceptor);
        invocation.addInterceptor(methodAfterReturningInterceptor);

        Object result = invocation.proceed();
        System.out.println("执行的结果:" + result);


    }

}
