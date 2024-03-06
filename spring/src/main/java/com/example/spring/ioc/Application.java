package com.example.spring.ioc;

import com.example.spring.ioc.postprocessor.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/28 9:40 AM
 * @desc
 */
public class Application {


    private final List<BeanPostProcessor> processors = new ArrayList<>();


    public void createBean(String beanName) {


    }

    Object applyBeanPostProcessorBeforeInitialization(Object bean) {
        Object result = bean;
        for (BeanPostProcessor processor : processors) {
            Object current = processor.postProcessBeforeInitialization(result);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    Object applyBeanPostProcessorAfterInitialization(Object bean) {
        Object result = bean;
        for (BeanPostProcessor processor : processors) {
            Object current = processor.postProcessAfterInitialization(result);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

}
