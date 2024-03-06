package org.javaboy.mybatis.enhancer.plugins.classlevel.demo;

/**
 * @author majin.wj
 * @date 2023/6/9 4:04 PM
 * @desc
 */
public class StudentService implements IStudentService {
    @Override
    public String hello(String arg) {
        System.out.println("hello 方法执行");
        return "hello" + arg;
    }

    @Override
    public void hi(String arg1, Integer arg2) {
        System.out.println("hi方法执行:arg1: " + arg1 + " arg2:" + arg2);
    }
}
