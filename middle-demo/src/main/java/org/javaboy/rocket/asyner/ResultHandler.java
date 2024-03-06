package org.javaboy.rocket.asyner;

/**
 * @author chengxl
 * @date 2020/9/8
 */
public interface ResultHandler<T> {

    void process(T result, AsyncallContext context);
}
