package org.javaboy.rocket.asyner;

/**
 * @author chengxl
 * @date 2020/9/8
 */
public interface AsyncSupplier<T> {

    T get(Object[] args);
}
