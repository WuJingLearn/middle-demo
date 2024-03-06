package org.javaboy.rocket.asyner;

/**
 * @author chengxl
 * @date 2020/9/8
 */
public interface ErrorHandler {

    void process(Throwable throwable, AsyncallContext context);
}
