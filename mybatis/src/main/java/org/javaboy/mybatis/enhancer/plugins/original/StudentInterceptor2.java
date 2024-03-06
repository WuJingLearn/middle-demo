package org.javaboy.mybatis.enhancer.plugins.original;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

/**
 * @author majin.wj
 * @date 2023/6/9 2:02 PM
 * @desc
 */
@Intercepts({@Signature(type = IStudentService.class,method = "hello",args = {String.class})})
public class StudentInterceptor2 implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("StudentInterceptor2 hello方法执行之前");
        Object proceed = invocation.proceed();
        System.out.println("StudentInterceptor2 hello方法执行后");
        return proceed;
    }
}
