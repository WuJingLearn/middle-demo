package org.javaboy.mybatis.enhancer.plugins.methodlevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author majin.wj
 * @date 2023/6/9 2:35 PM
 * @desc 标识增强
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercept {

    String value() default "";

    Class<?> type();

    String method();

    Class<?>[] argTypes() default {};

}
