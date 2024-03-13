package org.javaboy.utils.lock;

import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by xianliang on 18/2/13.
 *
 * @author xianliang
 * @date 2018/02/13
 */
public interface LockErrorHandler {

    Object process(String key, MethodInvocation invocation, Throwable throwable) throws Throwable;
}
