package org.javaboy.utils.lock;

/**
 * @author xianliang
 * @date 2017/03/11
 */
public class IdempotentLockAttribute extends SimpleLockAttribute {

    private int lifecycle;

    private boolean ignoreException;

    private ByteConverter converter = new DefaultByteConverter();

    public int getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(int lifecycle) {
        this.lifecycle = lifecycle;
    }

    public boolean isIgnoreException() {
        return ignoreException;
    }

    public void setIgnoreException(boolean ignoreException) {
        this.ignoreException = ignoreException;
    }

    public ByteConverter getConverter() {
        return converter;
    }

    public void setConverter(ByteConverter converter) {
        this.converter = converter;
    }
}
