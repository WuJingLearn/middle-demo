package org.javaboy.utils.lock;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Method;

/**
 * Created by xianliang on 17/11/27.
 *
 * @author xianliang
 * @date 2017/11/27
 */
@Deprecated
public class DistributedLockAutoProxyCreator extends AbstractAutoProxyCreator {

    /**
     * 是否启用tair加锁代理
     */
    @Value("${distributed.lock.enabled:true}")
    private boolean enabled;
    @Autowired
    private LockAttributeSource lockAttributeSource;
    @Autowired
    private LockAspectSupport lockInterceptor;

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
        if (enabled && lockInterceptor != null && matches(beanClass)) {
            return new Object[]{lockInterceptor};
        }
        return null;
    }

    public boolean matches(Class<?> targetClass) {
        for (Method method : targetClass.getMethods()) {
            if (matches(method, targetClass)) {
                return true;
            }
        }
        return false;
    }

    public boolean matches(Method method, Class<?> targetClass) {
        if(method.getAnnotation(DistributedLock.class) == null) {
            return false;
        }
        LockAttribute lockAttribute = lockAttributeSource.getLockAttribute(method, targetClass);
        boolean matched = lockAttribute != null;
        if (matched) {
            lockInterceptor.warmup(targetClass, method, lockAttribute.key());
            if(logger.isDebugEnabled()) {
                logger.debug(String.format("LockAttributeSourcePointcut matched=>%s#%s", targetClass.getName(), method.getName()));
            }
        }
        return matched;
    }
}
