package org.javaboy.mybatis.enhancer.plugins.classlevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author majin.wj
 * @date 2023/6/9 2:35 PM
 * @desc 增强什么类型
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercept {

    Class<?> type();


}
