package org.javaboy.mybatis.enhancer.plugins.methodlevel.demo;


import org.javaboy.mybatis.enhancer.plugins.methodlevel.Intercept;
import org.javaboy.mybatis.enhancer.plugins.methodlevel.Interceptor;
import org.javaboy.mybatis.enhancer.plugins.methodlevel.Invocation;

/**
 * @author majin.wj
 * @date 2023/6/9 2:02 PM
 * @desc
 */

@Intercept(type = StudentService.class, method = "hello")
public class StudentInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("hello方法执行之前");
        Object proceed = invocation.proceed();
        System.out.println("hello方法执行后");
        return proceed;
    }
}
