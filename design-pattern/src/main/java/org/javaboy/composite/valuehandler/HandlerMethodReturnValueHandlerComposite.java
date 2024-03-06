package org.javaboy.composite.valuehandler;


import java.util.ArrayList;
import java.util.List;

/**
 * 组合设计模式：
 * 将一组对象组织成树形结构，以表示一种部分-整体的层次结构
 * 客户端可以使用一个单一对象，来处理组合对象的逻辑
 */
public class HandlerMethodReturnValueHandlerComposite implements ReturnValueHandler {

    private List<ReturnValueHandler> returnValueHandlers = new ArrayList<>();

    @Override
    public boolean supportsReturnType(ReturnValue returnValue) {
        return getReturnValueHandler(returnValue) == null;
    }

    private ReturnValueHandler getReturnValueHandler(ReturnValue returnValue) {
        for (ReturnValueHandler returnValueHandler : returnValueHandlers) {
            if (returnValueHandler.supportsReturnType(returnValue)) {
                return returnValueHandler;
            }
        }
        return null;
    }


    @Override
    public void handleReturnValue(Object value, ReturnValue returnValue) {
        ReturnValueHandler returnValueHandler = getReturnValueHandler(returnValue);
        if (returnValueHandler == null) {
            throw new IllegalArgumentException("Unknown return value type: " + returnValue.getReturnType());
        }
        returnValueHandler.handleReturnValue(value, returnValue);
    }
}
