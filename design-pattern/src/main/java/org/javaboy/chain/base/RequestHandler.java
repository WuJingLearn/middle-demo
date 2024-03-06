package org.javaboy.chain.base;

public interface RequestHandler {

    boolean support(RequestType type);


    void handle(RequestContext context);

    boolean exceptionInterrupt();

}
