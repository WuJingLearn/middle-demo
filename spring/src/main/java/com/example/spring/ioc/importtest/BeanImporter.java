package com.example.spring.ioc.importtest;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import java.util.Set;

/**
 * @author:majin.wj
 */
public class BeanImporter implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Set<MethodMetadata> packages = importingClassMetadata.getAnnotatedMethods("packages");
        System.out.println(packages);
    }
}
