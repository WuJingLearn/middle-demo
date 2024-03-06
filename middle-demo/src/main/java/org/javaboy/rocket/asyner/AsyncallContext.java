package org.javaboy.rocket.asyner;

import java.lang.reflect.Method;

/**
 * @author chengxl
 * @date 2020/9/8
 */
public class AsyncallContext {

    /**
     * 重试次数
     */
    private int retryCount;
    private Class targetClass;
    private Method targetMethod;
    private Object target;
    private Object[] args;
    private AsyncOperation asyncOperation;

    public void invoke() throws Throwable {
        Class<?> returnType = targetMethod.getReturnType();

        if (returnType == void.class || returnType == Void.class) {
            targetMethod.invoke(target, args);
        } else {
            Object result = targetMethod.invoke(target, args);
            if (asyncOperation.getResultHandler() != null) {
                asyncOperation.getResultHandler().process(result, this);
            }
        }
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setAsyncOperation(AsyncOperation asyncOperation) {
        this.asyncOperation = asyncOperation;
    }

    public AsyncOperation getAsyncOperation() {
        return asyncOperation;
    }

    public ErrorHandler getErrorHandler() {
        return asyncOperation.getErrorHandler();
    }
}
