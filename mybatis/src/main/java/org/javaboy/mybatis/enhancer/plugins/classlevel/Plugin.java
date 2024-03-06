package org.javaboy.mybatis.enhancer.plugins.classlevel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author majin.wj
 * @date 2023/6/9 2:48 PM
 * @desc
 */
public class Plugin implements InvocationHandler {

    private Interceptor interceptor;
    private Object target;

    public Plugin(Interceptor interceptor, Object target) {
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
        Class<?> interfaceClass = getInterceptClass(interceptor);
        Class<?> interceptClass = null;
        Class<?>[] interfaces = target.getClass().getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            if (interfaceClass == interfaces[i]) {
                interceptClass = interfaceClass;
                break;
            }
        }
        if (interceptClass != null) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{interfaceClass}, new Plugin(interceptor, target));
        }
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return interceptor.intercept(new Invocation(target, method, args));
    }

    public static Class<?> getInterceptClass(Interceptor interceptor) {
        Intercept intercept = interceptor.getClass().getAnnotation(Intercept.class);
        if (intercept == null) {
            throw new IllegalStateException("No intercept annotation in interceptor " + intercept.getClass());
        }
        return intercept.type();
    }

}
