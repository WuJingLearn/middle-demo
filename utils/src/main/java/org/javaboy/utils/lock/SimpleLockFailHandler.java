package org.javaboy.utils.lock;

import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by xianliang on 17/3/23.
 *
 * @author xianliang
 * @date 2017/03/23
 */
public class SimpleLockFailHandler implements LockFailHandler, LockErrorHandler {

    @Override
    public Object process(String key, MethodInvocation invocation) {
        throw new RuntimeException(key + " is locked");
    }

    @Override
    public Object process(String key, MethodInvocation invocation, Throwable throwable) throws Throwable {
        if(throwable != null) {
            throw throwable;
        }
        throw new RuntimeException(key + " lock error");
    }
}
