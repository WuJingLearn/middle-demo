package org.javaboy.utils.lock;

import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by xianliang on 17/3/23.
 *
 * @author xianliang
 * @date 2017/03/23
 */
public interface LockFailHandler {

    Object process(String key, MethodInvocation invocation) throws Throwable;
}
