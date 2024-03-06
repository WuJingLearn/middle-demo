package org.javaboy.mybatis.enhancer.plugins.original;

import org.apache.ibatis.plugin.InterceptorChain;

/**
 * @author majin.wj
 * @date 2023/6/9 2:04 PM
 * @desc
 */
public class Test {
    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        //构建责任链
        InterceptorChain chain = new InterceptorChain();
        chain.addInterceptor(new StudentInterceptor2());
        chain.addInterceptor(new StudentInterceptor());
        //增强
        Object proxy = chain.pluginAll(studentService);
        IStudentService proxy1 = (IStudentService) proxy;
        String zs = proxy1.hello("zs");


    }
}
