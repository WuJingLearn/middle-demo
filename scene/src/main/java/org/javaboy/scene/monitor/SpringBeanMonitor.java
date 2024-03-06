package org.javaboy.scene.monitor;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class SpringBeanMonitor<T> implements ApplicationContextAware {

    private ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public Map<String,T> getBeanOfType(Class<T> type) {
        Map<String, T> beanMap = (Map<String, T>) context.getBeansOfType(type);
        return beanMap;
    }


}
