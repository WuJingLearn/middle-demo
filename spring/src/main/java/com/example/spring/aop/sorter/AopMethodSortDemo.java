package com.example.spring.aop.sorter;

import org.aspectj.lang.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 通知需要按照环绕通知，前置通知，after最后，afterReturn通知，afterThrowing通知进行排序；
 * <p>
 * ## 根据通知顺序进行排序
 * org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory#getAdvisorMethods(java.lang.Class)
 * <p>
 * <p>
 * <p>
 * 获取所有advisor
 * org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator#shouldSkip(java.lang.Class, java.lang.String)
 * <p>
 * <p>
 * 根据@Order注解进行排序
 * org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator#findEligibleAdvisors(java.lang.Class, java.lang.String)
 * <p>
 * <p>
 * 通知方法的排序：
 * <p>
 * 1.拿到通知的注解类型，
 * 2.注解类型预先定义好排序顺序
 * 3.拿到对应注解类型，进行排序
 */
public class AopMethodSortDemo {


    @Around(value = "")
    public void m1() {
    }

    @Before("")
    public void m2() {
    }

    @After("")
    public void m3() {
    }

    @AfterReturning("")
    public void m4() {
    }

    @AfterThrowing("")
    public void m5() {
    }

    static List<Class> sortClass = Arrays.asList(Around.class, Before.class, After.class, AfterReturning.class, AfterThrowing.class);


    static int getOrder(Method method) {
        for (int i = 0; i < sortClass.size(); i++) {
            Annotation annotation = method.getAnnotation(sortClass.get(i));
            if (annotation != null) {
                return i;
            }
        }
        throw new RuntimeException("error invke");
    }

    public static void main(String[] args) throws NoSuchMethodException {

        Comparator<Method> comparator = new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                return getOrder(m1) - getOrder(m2);
            }
        };

        List<Method> methods = new ArrayList<>();
        findAdviceMethod(AopMethodSortDemo.class, methods::add);
//        Method m1 = AopMethodSortDemo.class.getMethod("m1");
//        Method m2 = AopMethodSortDemo.class.getMethod("m2");
//        Method m3 = AopMethodSortDemo.class.getMethod("m3");
//        Method m4 = AopMethodSortDemo.class.getMethod("m4");
//        Method m5 = AopMethodSortDemo.class.getMethod("m5");
//        methods.add(m1);
//        methods.add(m2);
//        methods.add(m3);
//        methods.add(m4);
//        methods.add(m5);
        methods.sort(comparator);

        methods.forEach(item -> System.out.println(item.getName()));
    }

    static void findAdviceMethod(Class<?> cls, MethodCallback callback) {
        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {
            for (int i = 0; i < sortClass.size(); i++) {
                Annotation annotation = method.getAnnotation(sortClass.get(i));
                if (annotation != null) {
                    System.out.println(method.getName() + "是通知方法");
                    callback.doWith(method);
                }
            }
        }
    }

    interface MethodCallback {
        void doWith(Method method);
    }

}