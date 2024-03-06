package org.javaboy.rocket.asyner;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xianliang on 2019-08-07.
 *
 * @author xianliang
 * @date 2019/08/07
 */
public class AsyncMethodPointcutAdvisor extends AbstractPointcutAdvisor {

    @Autowired
    private AsyncMethodInterceptor asyncMethodInterceptor;

    @Override
    public Pointcut getPointcut() {
        return AnnotationMatchingPointcut.forMethodAnnotation(Asynchronized.class);
    }

    @Override
    public Advice getAdvice() {
        return asyncMethodInterceptor;
    }
}
