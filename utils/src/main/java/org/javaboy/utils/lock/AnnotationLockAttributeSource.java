package org.javaboy.utils.lock;

import java.lang.reflect.Method;

/**
 * 从DistributedLock注解上获取LockAttribute
 *
 * @author xianliang
 * @date 2017/03/11
 */
public class AnnotationLockAttributeSource extends AbstractLockAttributeSource {

    @Override
    protected LockAttribute findLockAttribute(Method method) {
        LockAttribute lockAttribute = determineLockAttribute(method);
        return lockAttribute;
    }

    protected LockAttribute determineLockAttribute(Method ae) {
        if (ae.getAnnotations().length > 0) {

            SimpleLockAttribute lockAttribute = getLockAttribute(ae);
            if(lockAttribute == null) {
                return null;
            }
            return lockAttribute;
        }
        return null;
    }

    private SimpleLockAttribute getLockAttribute(Method ae) {
        DistributedLock distributedLock = ae.getAnnotation(DistributedLock.class);
        if(distributedLock != null) {
            SimpleLockAttribute lockAttribute = new SimpleLockAttribute();
            lockAttribute.setKey(distributedLock.key());
            lockAttribute.setTimeout(distributedLock.timeout());
            lockAttribute.setFailHandler(newInstance(distributedLock.failHandlerClass()));
            lockAttribute.setErrorHandler(newInstance(distributedLock.errorHandlerClass()));
            lockAttribute.setReturnType(ae.getReturnType());
            return lockAttribute;
        }

        IdempotentLock idempotentLock = ae.getAnnotation(IdempotentLock.class);
        if(idempotentLock != null) {
            IdempotentLockAttribute lockAttribute = new IdempotentLockAttribute();
            lockAttribute.setKey(idempotentLock.key());
            lockAttribute.setTimeout(idempotentLock.timeout());
            lockAttribute.setFailHandler(newInstance(idempotentLock.failHandlerClass()));
            lockAttribute.setErrorHandler(newInstance(idempotentLock.errorHandlerClass()));
            lockAttribute.setReturnType(ae.getReturnType());
            lockAttribute.setLifecycle(idempotentLock.lifecycle());
            lockAttribute.setIgnoreException(idempotentLock.ignoreException());
            try {
                lockAttribute.setConverter(idempotentLock.converterType().newInstance());
            } catch (Throwable e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            return lockAttribute;
        }

        return null;
    }

    private <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
