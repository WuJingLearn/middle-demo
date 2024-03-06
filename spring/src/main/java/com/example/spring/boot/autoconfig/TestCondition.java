package com.example.spring.boot.autoconfig;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TestCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        System.out.println("触发时机");
        return true;
    }


    /**
     * 在扫描beanDefainiton的时候，会进行判断，拿到这个类上的所有Conditional注解。
     * 拿到Conditional注解中Condition这个类，调用match方法是false的话，
     * 这个类就不会创建成BeanDefinition.
     *
     * SpringBoot-starter自动配置中：
     * 是通过ImportSelector的方式，导入spring.factories中配置的类名称，
     * 然后在构建BeanDefinition的时候，也会去使用上面的机制，拿到类上面的Conditional注解，来进行校验
     * 如果校验不通过，则不为这个类创建BeanDefinition
     *
     */
//    public boolean shouldSkip(@Nullable AnnotatedTypeMetadata metadata, @Nullable ConfigurationCondition.ConfigurationPhase phase) {
//        if (metadata == null || !metadata.isAnnotated(Conditional.class.getName())) {
//            return false;
//        }
//
//        if (phase == null) {
//            if (metadata instanceof AnnotationMetadata &&
//                    ConfigurationClassUtils.isConfigurationCandidate((AnnotationMetadata) metadata)) {
//                return shouldSkip(metadata, ConfigurationCondition.ConfigurationPhase.PARSE_CONFIGURATION);
//            }
//            return shouldSkip(metadata, ConfigurationCondition.ConfigurationPhase.REGISTER_BEAN);
//        }
//
//        List<Condition> conditions = new ArrayList<>();
//        for (String[] conditionClasses : getConditionClasses(metadata)) {
//            for (String conditionClass : conditionClasses) {
//                Condition condition = getCondition(conditionClass, this.context.getClassLoader());
//                conditions.add(condition);
//            }
//        }
//
//        AnnotationAwareOrderComparator.sort(conditions);
//
//        for (Condition condition : conditions) {
//            ConfigurationCondition.ConfigurationPhase requiredPhase = null;
//            if (condition instanceof ConfigurationCondition) {
//                requiredPhase = ((ConfigurationCondition) condition).getConfigurationPhase();
//            }
//            if ((requiredPhase == null || requiredPhase == phase) && !condition.matches(this.context, metadata)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
}

