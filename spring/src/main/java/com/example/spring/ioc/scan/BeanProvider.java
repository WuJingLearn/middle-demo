package com.example.spring.ioc.scan;

/**
 * @author:majin.wj
 */
public interface BeanProvider<T> {
    public T getProvider();
}
