package org.javaboy.chain.base.support;

import org.javaboy.chain.base.RequestContext;
import org.javaboy.chain.base.RequestType;

public class NothingHandler extends AbstractHandler {
    @Override
    public boolean support(RequestType type) {
        return true;
    }


    @Override
    public boolean exceptionInterrupt() {
        return true;
    }

    @Override
    protected void doHandle(RequestContext context) {
        System.out.println("打印一句话备");
    }
}
