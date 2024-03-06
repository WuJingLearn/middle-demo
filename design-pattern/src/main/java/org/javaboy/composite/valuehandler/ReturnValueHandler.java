package org.javaboy.composite.valuehandler;

public interface ReturnValueHandler {


    boolean supportsReturnType(ReturnValue returnValue);

    void handleReturnValue(Object value, ReturnValue returnValue);

}
