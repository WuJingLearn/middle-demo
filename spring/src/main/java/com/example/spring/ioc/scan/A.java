package com.example.spring.ioc.scan;

/**
 * @author:majin.wj
 */
public class A implements BeanProvider<B>{
    @Override
    public B getProvider() {
        return new B();
    }
}
