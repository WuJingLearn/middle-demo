package org.javaboy.utils.lock;

import java.lang.reflect.Method;

/**
 * 获取方法上LockAttribute的策略接口定义
 *
 * @author xianliang
 * @date 2017/03/11
 */
public interface LockAttributeSource {

    LockAttribute getLockAttribute(Method method, Class<?> targetClass);

}
