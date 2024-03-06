package com.example.spring.aop.sourcecode.sample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author majin.wj
 * @date 2023/6/28 5:01 PM
 * @desc
 */
public class MethodAfterReturningInterceptor extends AbstractAdvice implements MethodInterceptor {

    public MethodAfterReturningInterceptor(Method adviceMethod, Object aspectBean) {
        super(adviceMethod, aspectBean);
    }

    @Override
    public Object invoke(Invocation invocation) {
        Object retValue = invocation.proceed();
        //正常返回后执行
        invokeAdviceMethod();
        return retValue;

    }

    public static void main(String[] args) {


        Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{JoinPoint.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getDeclaringClass() == Object.class) {
                    System.out.println("object的方法"+method);
                    return "a";
                }
                System.out.println("hello"+proxy.getClass());
                return null;
            }
        });

        JoinPoint o1 = (JoinPoint) o;
        Method method = o1.method();

    }
}
