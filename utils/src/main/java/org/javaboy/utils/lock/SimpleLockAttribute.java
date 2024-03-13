package org.javaboy.utils.lock;

/**
 * @author xianliang
 * @date 2017/03/11
 */
public class SimpleLockAttribute implements LockAttribute {
    /**
     * 锁的key
     */
    private String key;
    /**
     * 锁的有效时间
     */
    private int timeout;
    /**
     * 加锁失败处理器
     */
    private LockFailHandler failHandler;

    private LockErrorHandler errorHandler;

    private Class returnType;

    @Override
    public String key() {
        return key;
    }

    @Override
    public int timeout() {
        return timeout;
    }

    @Override
    public LockFailHandler failHandler() {
        return failHandler;
    }

    @Override
    public LockErrorHandler errorHandler() {
        return errorHandler;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setFailHandler(LockFailHandler handler) {
        this.failHandler = handler;
    }

    public void setErrorHandler(LockErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public Class getReturnType() {
        return returnType;
    }

    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }
}
