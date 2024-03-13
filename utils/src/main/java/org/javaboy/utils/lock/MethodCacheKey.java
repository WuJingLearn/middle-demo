package org.javaboy.utils.lock;

import java.lang.reflect.Method;

/**
 * Created by xianliang on 2019-08-25.
 *
 * @author xianliang
 * @date 2019/08/25
 */
public class MethodCacheKey implements Comparable<MethodCacheKey> {

    private final Method method;

    private final int hashCode;

    public MethodCacheKey(Method method) {
        this.method = method;
        this.hashCode = method.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return (this == other || (other instanceof MethodCacheKey &&
            this.method == ((MethodCacheKey) other).method));
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public String toString() {
        return this.method.toString();
    }

    @Override
    public int compareTo(MethodCacheKey other) {
        int result = this.method.getName().compareTo(other.method.getName());
        if (result == 0) {
            result = this.method.toString().compareTo(other.method.toString());
        }
        return result;
    }
}
