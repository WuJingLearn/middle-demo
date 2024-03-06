package org.javaboy.mybatis.enhancer.plugins.methodlevel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author majin.wj
 * @date 2023/6/9 2:36 PM
 * @desc
 */
public class Invocation {
    private Object target;
    private Method method;
    private Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }

}
