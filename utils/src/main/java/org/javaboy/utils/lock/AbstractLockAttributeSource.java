package org.javaboy.utils.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.MethodClassKey;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LockAttributeSource的抽象实现，带缓存功能
 *
 * @author xianliang
 * @date 2017/03/11
 */
public abstract class AbstractLockAttributeSource implements LockAttributeSource {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final static LockAttribute NULL_LOCK_ATTRIBUTE = new SimpleLockAttribute();

    final Map<Object, LockAttribute> attributeCache = new ConcurrentHashMap<Object, LockAttribute>(1024);

    @Override
    public LockAttribute getLockAttribute(Method method, Class<?> targetClass) {
        Object cacheKey = getCacheKey(method, targetClass);
        Object cached = this.attributeCache.get(cacheKey);
        if (cached != null) {
            if (cached == NULL_LOCK_ATTRIBUTE) {
                return null;
            } else {
                return (LockAttribute) cached;
            }
        } else {
            LockAttribute lockAttribute = computeLockAttribute(method, targetClass);
            if (lockAttribute == null) {
                this.attributeCache.put(cacheKey, NULL_LOCK_ATTRIBUTE);
            } else {
                if (logger.isDebugEnabled()) {
                    Class<?> classToLog = (targetClass != null ? targetClass : method.getDeclaringClass());
                    logger.debug("Adding transactional method '" + classToLog.getSimpleName() + "." + method.getName()
                            + "' with attribute: " + lockAttribute);
                }
                this.attributeCache.put(cacheKey, lockAttribute);
            }
            return lockAttribute;
        }
    }

    private LockAttribute computeLockAttribute(Method method, Class<?> targetClass) {
        if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
            return null;
        }

        // 目标类可能是被CGLIB代理后的子类，需要获取原class
        Class<?> userClass = ClassUtils.getUserClass(targetClass);
        // 如果method是接口方法，取实现类的method
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, userClass);
        // 处理带泛型参数的方法，取原方法
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        // 获取方法的LockAttribute
        LockAttribute lockAttribute = findLockAttribute(specificMethod);
        if (lockAttribute != null) {
            return lockAttribute;
        }
        // 如果实现的类的方法上不存在LockAttribute，则退回到尝试取接口上的LockAttribute
        if (specificMethod != method) {
            lockAttribute = findLockAttribute(method);
            if (lockAttribute != null) {
                return lockAttribute;
            }
        }
        return null;
    }

    protected abstract LockAttribute findLockAttribute(Method method);

    protected Object getCacheKey(Method method, Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }

    protected boolean allowPublicMethodsOnly() {
        return false;
    }

}
