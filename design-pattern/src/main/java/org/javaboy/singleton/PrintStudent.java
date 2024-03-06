package org.javaboy.singleton;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 破环单列：可以使用反射，反序列，克隆等方式，
 * 但是本质上还是通过反射的方式来创建新的对象的。jdk,json的反序列都是。克隆估计也是，克隆无法看到代码
 */
public class PrintStudent {

    private static PrintStudent student = new PrintStudent();

    private PrintStudent(){}

    private void hello(){
        System.out.println("hello");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String jsonString = JSON.toJSONString(student);

        // json反序列，通过反射拿到构造器，然后执行构造方法创建对象。
        PrintStudent printStudent = JSON.parseObject(jsonString, PrintStudent.class);
        System.out.println(printStudent);

        // 通过反射也可以破环单例模式
        Constructor<PrintStudent> constructor = PrintStudent.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        PrintStudent printStudent1 = constructor.newInstance();
        System.out.println(printStudent1);
        // 通过反射执行私有方法
        Method helloMethod = PrintStudent.class.getDeclaredMethod("hello");
        helloMethod.setAccessible(true);

        helloMethod.invoke(printStudent1);

    }
}
