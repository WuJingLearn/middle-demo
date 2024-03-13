package org.javaboy.utils.asynccall;

import com.taobao.metaq.client.MetaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.core.env.Environment;

/**
 * @see CacheInterceptor
 * @author
 * @date 2019/08/07
 */
public abstract class AsyncAspectSupport implements BeanFactoryAware, InitializingBean, DisposableBean, SmartInitializingSingleton {

    private static final String ASYNC_METAQ_PRODUCER_UNITNAME = "async.metaq.producer.unitname";

    protected static Logger logger = LoggerFactory.getLogger(AsyncAspectSupport.class);

    protected boolean initialized = false;

    protected MetaProducer metaProducer;

    @Autowired
    protected AsyncProperties properties;

    @Autowired
    private Environment environment;

    public Class<?> getTargetClass(Object target) {
        return AopProxyUtils.ultimateTargetClass(target);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AsyncUtils.setBeanFactory(beanFactory);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        metaProducer = new MetaProducer(properties.getAppName() + "com.alibaba.boot.common.async.AsyncMethodInterceptor");
        if (environment.getProperty(ASYNC_METAQ_PRODUCER_UNITNAME) != null) {
            metaProducer.setUnitName(environment.getProperty(ASYNC_METAQ_PRODUCER_UNITNAME));
        }
        metaProducer.start();
    }

    @Override
    public void destroy() throws Exception {
        metaProducer.shutdown();
    }

    @Override
    public void afterSingletonsInstantiated() {
        initialized = true;
    }
}
