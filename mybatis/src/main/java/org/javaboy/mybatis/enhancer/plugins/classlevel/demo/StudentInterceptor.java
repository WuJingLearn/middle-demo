package org.javaboy.mybatis.enhancer.plugins.classlevel.demo;


import org.javaboy.mybatis.enhancer.plugins.classlevel.Intercept;
import org.javaboy.mybatis.enhancer.plugins.classlevel.Interceptor;
import org.javaboy.mybatis.enhancer.plugins.classlevel.Invocation;

/**
 * @author majin.wj
 * @date 2023/6/9 4:04 PM
 * @desc
 */
@Intercept(type = IStudentService.class)
public class StudentInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("StudentInterceptor 执行 before");
        return invocation.proceed();
    }
}
