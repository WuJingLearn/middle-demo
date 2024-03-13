package org.javaboy.utils.asynccall;

import org.springframework.core.MethodClassKey;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengxl
 * @date 2020/9/8
 */
public class AsyncOperationSource {

    private final Map<Object, AsyncOperation> attributeCache = new ConcurrentHashMap<>(1024);
    private static final AsyncOperation NULL_CACHING_ATTRIBUTE = new AsyncOperation();

    public AsyncOperation getAsyncOperation(Method method, Class<?> targetClass) throws Exception {
        if (method.getDeclaringClass() == Object.class) {
            return null;
        }

        Object cacheKey = getCacheKey(method, targetClass);
        AsyncOperation cached = this.attributeCache.get(cacheKey);

        if (cached != null) {
            return (cached != NULL_CACHING_ATTRIBUTE ? cached : null);
        }
        else {
            AsyncOperation cacheOps = computeAsyncOperations(method, targetClass);
            if (cacheOps != null) {
                this.attributeCache.put(cacheKey, cacheOps);
            }
            else {
                this.attributeCache.put(cacheKey, NULL_CACHING_ATTRIBUTE);
            }
            return cacheOps;
        }
    }

    private AsyncOperation computeAsyncOperations(Method method, Class<?> targetClass) throws Exception {
        Asynchronized asynchronized = method.getDeclaredAnnotation(Asynchronized.class);
        AsyncOperation asyncOperation = new AsyncOperation();
        asyncOperation.setSignature(AsyncUtils.getSignature(method));
        asyncOperation.setSerializingConverter(new SerializingConverter(asynchronized.serializer().newInstance()));
        asyncOperation.setDeserializingConverter(new DeserializingConverter(asynchronized.deserializer().newInstance()));
        asyncOperation.setResultHandler(asynchronized.callback().newInstance());
        asyncOperation.setAsyncSupplier(asynchronized.defaultResult().newInstance());
        asyncOperation.setErrorHandler(asynchronized.errorHandler().newInstance());
        return asyncOperation;
    }


    protected Object getCacheKey(Method method, Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }


}
