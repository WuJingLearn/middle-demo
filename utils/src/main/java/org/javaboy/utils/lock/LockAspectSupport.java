package org.javaboy.utils.lock;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.MethodBasedEvaluationContext;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 实现LockInterceptor通用逻辑
 *
 * @author xianliang
 * @date 2017/03/12
 */
public class LockAspectSupport<T extends LockAttribute> implements MethodInterceptor {

    protected final static Logger logger = LoggerFactory.getLogger("distributedLockLogger");

    private SimpleExpressionEvaluator evaluator = new SimpleExpressionEvaluator();

    @Autowired
    protected LockAttributeSource lockAttributeSource;
    @Autowired
    protected LockService lockService;
    @Autowired
    protected BeanFactory beanFactory;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Method method = invocation.getMethod();
        Class clazz = invocation.getThis().getClass();

        T lockAttribute = (T)lockAttributeSource.getLockAttribute(method, clazz);

        if (lockAttribute == null) {
            return invocation.proceed();
        }

        /**
         * 获取一个随机数，用于在解锁时，保证自己释放的锁是自己之前加的锁，用于降低以下情况发生的概率：当A获取锁之后，业务操作阻塞时间过长导致锁被自动释放；
         * 此时B获取到了锁之后开始执行业务操作；A从阻塞中恢复，并释放了锁，其实释放的是B的锁，导致B的业务操作不再被锁的保护范围内。
         * 要完全避免这个问题，有一个可选的解决方法（目前未实现）：每获取一个锁的同时得到一个递增的token，业务操作时需要带上这个token，当发现某一个操作已经被一个更大的token操作过了，
         * 则需要拒绝这个操作。
         */
        long startTime = System.currentTimeMillis();
        String key = null;
        String value = lockService.assignLockValue();
        boolean success = false;
        try {
            key = eval(lockAttribute.key(), invocation);

            Object result = precheck(invocation, method, clazz, key, lockAttribute);
            if(result != null) {
                return result;
            }

            int timeout = lockAttribute.timeout();
            // 加锁
            if (!(success = lockService.lock(key, value, timeout, TimeUnit.SECONDS))) {
                //logger.warn("lock fail key={}", key);
                return lockAttribute.failHandler().process(key, invocation);
            }

            if(logger.isDebugEnabled()) {
                logger.debug("lock key={}, timeout={}", key, timeout);
            }
            // 业务操作必须在锁过期前完成，否则锁会被自动释放，那么业务操作就不再被保护范围，导致并发问题
            return process(invocation, method, clazz, key, lockAttribute);
        } catch (Throwable throwable) {
            //logger.error(String.format("lock error key=%s", key), throwable);
            return lockAttribute.errorHandler().process(key, invocation, throwable);
        } finally {
            // 解锁
            if(key != null && success) {
                success = lockService.unlock(key, value);
                if(logger.isDebugEnabled()) {
                    logger.debug("unlock key={}, success={}, elapsed={}ms", key, success, (System.currentTimeMillis() - startTime));
                }
            }
        }
    }

    protected Object precheck(MethodInvocation invocation, Method method, Class clazz, String key, T lockAttribute) {
        return null;
    }

    protected Object process(MethodInvocation invocation, Method method, Class clazz, String key, T lockAttribute) throws Throwable {
        return invocation.proceed();
    }

    protected String eval(String key, MethodInvocation invocation) {
        Method method = invocation.getMethod();
        Class clazz = invocation.getThis().getClass();
        MethodBasedEvaluationContext context =
                (MethodBasedEvaluationContext) this.evaluator.createMethodBasedEvaluationContext(method, invocation.getArguments(), beanFactory);
        context.setRootObject(invocation.getThis());
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, clazz);
        return evaluator.eval(key, methodKey, context);
    }

    /**
     * 提前预热一下，如预解析表达式，提高第一次执行时的效率
     * @param clazz
     * @param method
     * @param expression
     */
    public void warmup(Class clazz, Method method, String expression) {
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, clazz);
        evaluator.getExpression(methodKey, expression);
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkResult(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }
}
