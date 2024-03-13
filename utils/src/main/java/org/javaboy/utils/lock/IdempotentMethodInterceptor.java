package org.javaboy.utils.lock;

import com.alibaba.boot.distributed.lock.tair.TairLockProperties;
import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import com.taobao.tair.TairManager;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by xianliang on 2019-08-24.
 *
 * @author xianliang
 * @date 2019/08/24
 */
@Deprecated
public class IdempotentMethodInterceptor extends LockAspectSupport {

    private static final NullType NULL_VALUE = new NullType();

    private Map<MethodCacheKey, IdempotentLock> idempotentLockMap = new ConcurrentHashMap<>(64);

    @Autowired
    @Qualifier("tairManager")
    protected TairManager tairManager;
    @Autowired
    protected TairLockProperties properties;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Method method = invocation.getMethod();
        Class clazz = invocation.getThis().getClass();

        IdempotentLock idempotentLock = getIdempotentAnnotation(method);
        if (idempotentLock == null) {
            return invocation.proceed();
        }

        String key = null;
        String value = lockService.assignLockValue();
        try {

            key = eval(idempotentLock.key(), invocation);
            String idempotentKey = getIdempotentKey(clazz, method, key);

            Object result;
            //如果查询方法是否已经被成功执行过，则直接幂等返回结果
            if((result = getIdempotentValue(idempotentKey, idempotentLock.ignoreException())) != null) {
                return (method.getReturnType() == Void.TYPE || result instanceof NullType) ? null : result;
            }

            int timeout = idempotentLock.timeout();
            // 加锁
            if (!lockService.lock(key, value, timeout, TimeUnit.SECONDS)) {
                String message = "lock fail key=" + key;
                logger.error(message);
                throw new DistributedLockException(message);
            }

            //加完锁后，再查询方法是否已经被成功执行过
            if((result = getIdempotentValue(idempotentKey, idempotentLock.ignoreException())) != null) {
                return (method.getReturnType() == Void.TYPE || result instanceof NullType) ? null : result;
            }

            //执行实际的方法
            result = invocation.proceed();

            //缓存执行结果
            setIdempotentValue(idempotentKey, (Serializable)result, idempotentLock.lifecycle(), TimeUnit.SECONDS);

            return result;
        } catch (Throwable throwable) {
            logger.error(String.format("lock error key=%s", key), throwable);
            throw throwable;
        } finally {
            // 解锁
            if(key != null) {
                lockService.unlock(key, value);
            }
        }
    }

    private String getIdempotentKey(Class clazz, Method method, String key) {
        //类名 + 方法名 + key
        return clazz.getName() + "#" + method.getName() + "#" + key;
    }

    private IdempotentLock getIdempotentAnnotation(Method method) {
        MethodCacheKey cacheKey = new MethodCacheKey(method);
        IdempotentLock idempotentLock = idempotentLockMap.computeIfAbsent(cacheKey,
            key -> AnnotationUtils.findAnnotation(method, IdempotentLock.class));
        return idempotentLock;
    }

    private Object getIdempotentValue(String idempotentKey, boolean ignoreException) {
        Result<DataEntry> dataEntry;
        for(int retry = 0; retry < 3; ++retry) {
            dataEntry = tairManager.get(properties.getNamespace(), idempotentKey);
            ResultCode rc = dataEntry.getRc();

            if (ResultCode.DATANOTEXSITS.equals(rc)) {
                return null;
            }

            if (ResultCode.SUCCESS.equals(rc)) {
                return Optional.ofNullable(dataEntry.getValue())
                    .map(p -> p.getValue())
                    .orElse(null);
            }

            if(!needRetry(rc)) {
                break;
            }
        }
        String message = "getIdempotentValue, key=" + idempotentKey;
        logger.error(message);
        if(ignoreException) {
            return null;
        }
        throw new DistributedLockException(message);
    }

    private void setIdempotentValue(String idempotentKey, Serializable result, int lifecycle, TimeUnit unit) {
        if(result == null) {
            result = NULL_VALUE;
        }

        long timeout = unit.toSeconds(lifecycle);
        try {
            for(int retry = 0; retry < 3; ++retry) {
                ResultCode rc = tairManager.put(properties.getNamespace(), idempotentKey, result, 99, (int)timeout);
                if (ResultCode.SUCCESS.equals(rc) || ResultCode.VERERROR.equals(rc)) {
                    return;
                }
                if(!needRetry(rc)) {
                    break;
                }
            }
            logger.error("setIdempotentValue, key=" + idempotentKey);
        } catch (Exception e) {
            logger.error(e.getMessage() + ", key=" + idempotentKey, e);
        }
    }

    private boolean needRetry(ResultCode code) {
        if (code == null || ResultCode.CONNERROR.equals(code) || ResultCode.TIMEOUT.equals(code)
            || ResultCode.UNKNOW.equals(code)) {
            return true;
        }
        return false;
    }
}
