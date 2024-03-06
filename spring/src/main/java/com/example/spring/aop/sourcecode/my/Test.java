package com.example.spring.aop.sourcecode.my;

/**
 * @author majin.wj
 * @date 2023/6/28 3:08 PM
 * @desc
 */
public class Test {


    /**
     * 1.如果需要链式调用，需要统一接口， 这里统一成MethodInterceptor
     * 2.如果是静态增强，也就是如果确定了有哪些增强器，那么可以直接创建MethodInterceptor实现类，
     *   并在实现类中写代码逻辑即可，类似于Dubbo中Filter增强实现。
     * 3.
     *
     *
     *
     *
     * @param args
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws NoSuchMethodException {;
    }
}
