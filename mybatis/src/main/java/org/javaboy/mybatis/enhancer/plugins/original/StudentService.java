package org.javaboy.mybatis.enhancer.plugins.original;

/**
 * @author majin.wj
 * @date 2023/6/9 2:02 PM
 * @desc
 */
public class StudentService implements IStudentService {

    public String hello(String arg){
        System.out.println("hello方法执行成功");
        return "success: " + arg;
    }

}
