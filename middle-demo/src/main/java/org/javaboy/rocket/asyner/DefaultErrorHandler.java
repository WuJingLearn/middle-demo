package org.javaboy.rocket.asyner;

/**
 * @author chengxl
 * @date 2020/9/9
 */
public class DefaultErrorHandler implements ErrorHandler {

    @Override
    public void process(Throwable e, AsyncallContext context) {
        throw new RuntimeException(e.getMessage(), e);
    }
}
