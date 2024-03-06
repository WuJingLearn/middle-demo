package org.javaboy.chain.base.support;

import org.javaboy.chain.base.RequestContext;
import org.javaboy.chain.base.RequestHandler;

public abstract class AbstractHandler implements RequestHandler {

    @Override
    public void handle(RequestContext context) {
        if(support(context.getRequestType())) {
            doHandle(context);
        }
    }

    protected abstract void doHandle(RequestContext context);
}
