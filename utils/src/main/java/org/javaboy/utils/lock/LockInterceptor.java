package org.javaboy.utils.lock;

import org.aopalliance.intercept.MethodInterceptor;

import java.io.Serializable;

/**
 * 实现AOP的方法拦截器定义
 *
 * @author xianliang
 * @date 2017/03/11
 */
public interface LockInterceptor extends MethodInterceptor, Serializable {

    boolean lock(String key, Serializable value, int timeout, int retry);

    boolean unlock(String key, Serializable value, long expireTime, int retry);
}
