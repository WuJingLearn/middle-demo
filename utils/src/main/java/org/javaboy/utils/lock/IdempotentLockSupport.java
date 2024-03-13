package org.javaboy.utils.lock;

import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by xianliang on 15/3/2020.
 *
 * @author xianliang
 * @date 2020/03/15
 */
public abstract class IdempotentLockSupport extends LockAspectSupport<IdempotentLockAttribute> {

    @Override
    protected Object precheck(MethodInvocation invocation, Method method, Class clazz, String key, IdempotentLockAttribute lockAttribute) {
        String idempotentKey = getIdempotentKey(clazz, method, key);

        Object result;
        //如果查询方法是否已经被成功执行过，则直接幂等返回结果
        if ((result = getIdempotentValue(idempotentKey, lockAttribute)) != null) {
            return (method.getReturnType() == Void.TYPE || result instanceof NullType) ? null : result;
        }

        return null;
    }

    @Override
    protected Object process(MethodInvocation invocation, Method method, Class clazz, String key, IdempotentLockAttribute lockAttribute) throws Throwable {
        String idempotentKey = getIdempotentKey(clazz, method, key);

        Object result;
        //加完锁后，再查询方法是否已经被成功执行过
        if ((result = getIdempotentValue(idempotentKey, lockAttribute)) != null) {
            return (method.getReturnType() == Void.TYPE || result instanceof NullType) ? null : result;
        }

        //执行实际的方法
        result = invocation.proceed();

        //缓存执行结果
        setIdempotentValue(idempotentKey, (Serializable)result, lockAttribute);

        return result;
    }

    abstract protected Object getIdempotentValue(String idempotentKey, IdempotentLockAttribute lockAttribute);

    abstract protected void setIdempotentValue(String idempotentKey, Object result, IdempotentLockAttribute lockAttribute);

    protected String getIdempotentKey(Class clazz, Method method, String key) {
        //类名 + 方法名 + key
        return clazz.getName() + "#" + method.getName() + "#" + key;
    }
}
