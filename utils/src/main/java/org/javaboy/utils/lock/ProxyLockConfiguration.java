package org.javaboy.utils.lock;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Objects;

/**
 * 基于注解的类定义，定义一些基础的bean
 *
 * @author xianliang
 * @date 2017/03/11
 */
public abstract class ProxyLockConfiguration implements ImportAware {

    private AnnotationAttributes enableDistributedLock;

    @Value("${spring.aop.proxy-target-class:true}")
    private boolean proxyTargetClass;

    @Value("${distributed.lock.order:-2147483648}")
    private int order;

    @Bean
    public LockAttributeSource lockAttributeSource() {
        return new AnnotationLockAttributeSource();
    }

    @Bean
    public LockAspectSupport lockInterceptor() {
        LockAspectSupport lockInterceptor = new LockAspectSupport();
        return lockInterceptor;
    }

    @Bean
    public DistributedLockMethodPointcutAdvisor distributedLockMethodPointcutAdvisor(@Qualifier("lockInterceptor") LockAspectSupport lockInterceptor) {
        Objects.requireNonNull(lockInterceptor);
        DistributedLockMethodPointcutAdvisor advisor = new DistributedLockMethodPointcutAdvisor(lockInterceptor);
        advisor.setOrder(order);
        return advisor;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.enableDistributedLock =
                AnnotationAttributes.fromMap(importMetadata.getAnnotationAttributes(getAnnotationClass().getName(), false));
    }

    protected abstract Class getAnnotationClass();
}
