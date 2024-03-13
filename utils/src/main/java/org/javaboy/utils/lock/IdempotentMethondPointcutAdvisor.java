package org.javaboy.utils.lock;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * Created by xianliang on 2019-08-07.
 *
 * @author xianliang
 * @date 2019/08/07
 */
public class IdempotentMethondPointcutAdvisor extends AbstractPointcutAdvisor {

    private final LockAspectSupport interceptor;

    public IdempotentMethondPointcutAdvisor(LockAspectSupport interceptor) {
        this.interceptor = interceptor;
    }
    @Override
    public Pointcut getPointcut() {
        return AnnotationMatchingPointcut.forMethodAnnotation(IdempotentLock.class);
    }

    @Override
    public Advice getAdvice() {
        return interceptor;
    }
}
