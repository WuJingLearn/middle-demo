package com.example.spring.ioc.scan;

import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:majin.wj
 */
public class Test {


    @Bean
    public B b() {
        return new B();
    }


    public static void registerBeanAnnotationBeanDefinition(Class<?> cls) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Bean.class)) {
                Class<?> returnType = method.getReturnType();
                System.out.println("bean类型" + returnType);
            }
        }
    }

    static List<Class<?>> allClass = new ArrayList<>();

    public static void main(String[] args) {
        List<Class<?>> classList = Arrays.asList(A.class);

        allClass.addAll(classList);

        for (Class<?> aClass : allClass) {
            extraClass(aClass);
        }


        registerBeanAnnotationBeanDefinition(Test.class);


    }


    static void extraClass(Class<?> clazz) {
        // 处理当前bean类额外导入的
        List<Class<?>> r = new ArrayList<>();
        if (r.size() == 0) {
            return;
        }
        // 加入到扫描的类
        allClass.addAll(r);
        // 递归处理这个类其他的类
        for (Class<?> aClass : r) {
            extraClass(aClass);
        }

    }

}
