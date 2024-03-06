package org.javaboy.mybatis.enhancer.plugins.methodlevel.demo;

import org.javaboy.mybatis.enhancer.plugins.methodlevel.InterceptorChain;

/**
 * @author majin.wj
 * @date 2023/6/9 3:28 PM
 * @desc
 */
public class Test {

    public static void main(String[] args) {
        StudentService studentService = new StudentService();

        InterceptorChain interceptorChain = new InterceptorChain();
        interceptorChain.addInterceptor(new StudentInterceptor());
        interceptorChain.addInterceptor(new StudentInterceptor2());

        Object proxy = interceptorChain.plugin(studentService);
        IStudentService service = (IStudentService) proxy;

        String hello = service.hello("hello");
        System.out.println(hello);

    }
}
