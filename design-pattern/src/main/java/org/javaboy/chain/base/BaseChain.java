package org.javaboy.chain.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链有两种实现方式：使用链表或者使用数组来存储Handler
 * <p>
 * 责任链做为处理请求的入口，遍历所有处理器依次处理。在抽象处理器中，会判断是否需要处理该请求
 * exceptionInterrupt方法用来处理当处理器执行异常时，下个处理器是否还需要继续执行。
 */
public class BaseChain {

    List<RequestHandler> handlers = new ArrayList<>();


    public void execute(RequestContext context) {
        preChain(context);
        doChain(context);
        postChain(context);

    }

    void preChain(RequestContext context) {

    }

    void doChain(RequestContext context) {
        for (RequestHandler handler : handlers) {
            try {
                handler.handle(context);
            } catch (Exception e) {
                //log error
                if (handler.exceptionInterrupt()) {
                    break;
                }
            }
        }
    }

    void postChain(RequestContext context) {
    }
}
