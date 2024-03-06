package org.javaboy.boot.monitor;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public abstract class SpringBeanMonitor<T> implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public Map<String,T> getBeanOfType(Class<T> type) {
        return (Map<String, T>) context.getBeansOfType(type);
    }

}
