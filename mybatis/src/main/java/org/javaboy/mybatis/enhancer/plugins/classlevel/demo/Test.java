package org.javaboy.mybatis.enhancer.plugins.classlevel.demo;


import org.javaboy.mybatis.enhancer.plugins.classlevel.InterceptorChain;

/**
 * @author majin.wj
 * @date 2023/6/9 4:05 PM
 * @desc
 */
public class Test {
    public static void main(String[] args) {
        InterceptorChain chain = new InterceptorChain();

        StudentService service = new StudentService();


        StudentInterceptor interceptor = new StudentInterceptor();
        chain.addInterceptor(interceptor);

        Object proxy = chain.plugin(service);

        IStudentService studentService = (IStudentService) proxy;
        String hello = studentService.hello("hello");



        studentService.hi("虎牙",10);

    }
}
