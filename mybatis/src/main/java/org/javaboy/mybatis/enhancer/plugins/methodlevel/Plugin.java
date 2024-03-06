package org.javaboy.mybatis.enhancer.plugins.methodlevel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author majin.wj
 * @date 2023/6/9 2:48 PM
 * @desc
 */
public class Plugin implements InvocationHandler {

    private Map<Class<?>, Method> interceptMethod;
    private Interceptor interceptor;
    private Object target;

    public Plugin(Map<Class<?>, Method> interceptMethod, Interceptor interceptor, Object target) {
        this.interceptMethod = interceptMethod;
        this.interceptor = interceptor;
        this.target = target;
    }

    /**
     * 将interceptor增强逻辑和目标方法逻辑实现结合的方式，就是使用代理。
     *
     * @param interceptor
     * @param target
     * @return
     */
    public static Object wrap(Interceptor interceptor, Object target) {
        // 是否需要生成代理，取决于Interceptor中class是否是target的接口类型。
        Map<Class<?>, Method> interceptMethod = getInterceptMethod(interceptor);
        Class<?> interfaceClass = getInterface(interceptMethod, target);
        if (interfaceClass != null) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{interfaceClass}, new Plugin(interceptMethod, interceptor, target));
        }
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!interceptMethod.values().contains(method)) {
            return method.invoke(target, args);
        }
        return interceptor.intercept(new Invocation(target, method, args));
    }

    public static Map<Class<?>, Method> getInterceptMethod(Interceptor interceptor) {
        Intercept intercept = interceptor.getClass().getAnnotation(Intercept.class);
        if (intercept == null) {
            throw new IllegalStateException("No intercept annotation in interceptor " + intercept.getClass());
        }
        Map<Class<?>, Method> methodMap = new HashMap<>();
        try {
            Class<?> clazz = intercept.type();
            Method method = clazz.getMethod(intercept.method(), null/*intercept.argTyps()*/);
            methodMap.put(clazz, method);
            return methodMap;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 目前的设计是一个Interceptor只对一个方法进行增强处理。
     *
     * @param interceptMethod
     * @param target
     * @return
     */
    public static Class<?> getInterface(Map<Class<?>, Method> interceptMethod, Object target) {
        Class<?>[] interfaces = target.getClass().getInterfaces();

        Set<Class<?>> classes = interceptMethod.keySet();

        for (Class<?> interfaceClass : interfaces) {
            if (classes.contains(interfaceClass)) {
                return interfaceClass;
            }
        }
        return null;
    }

}
