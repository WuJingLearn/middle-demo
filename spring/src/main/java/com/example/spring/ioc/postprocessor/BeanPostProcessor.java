package com.example.spring.ioc.postprocessor;

/**
 * @author majin.wj
 * @date 2023/6/28 9:38 AM
 * @desc
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean);

    Object postProcessAfterInitialization(Object bean);

}
