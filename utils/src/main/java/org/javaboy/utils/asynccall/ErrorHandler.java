package org.javaboy.utils.asynccall;

/**
 * @author chengxl
 * @date 2020/9/8
 */
public interface ErrorHandler {

    void process(Throwable throwable, AsyncallContext context);
}
