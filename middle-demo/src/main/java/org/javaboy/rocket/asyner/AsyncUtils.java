package org.javaboy.rocket.asyner;

import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author chengxl
 * @date 2020/9/8
 */
public class AsyncUtils {

    private static final String PARAM_MARKING_PROJECT = "project.name";

    private static BeanFactory beanFactory;

    private static ThreadLocal<AsyncallContext> context = new ThreadLocal<>();

    public static boolean isAsyncCall() {
        return getContext() != null;
    }

    public static AsyncallContext getContext() {
        return context.get();
    }

    public static void setContext(AsyncallContext target) {
        context.set(target);
    }

    public static void remove() {
        context.remove();
    }

    public static String getParameterTypes(Method method) {
        return Arrays.asList(method.getParameterTypes()).stream()
                .map(p -> p.getCanonicalName())
                .collect(Collectors.joining(","));
    }

    public static String getSignature(Method method) {
        return method.getReturnType().getCanonicalName() + " " + method.getName() + "(" + getParameterTypes(method) + ")";
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public static void setBeanFactory(BeanFactory beanFactory) {
        AsyncUtils.beanFactory = beanFactory;
    }

    public static String getAppName() {
        String appName = getAppNameByProjectName();
        if (appName != null) {
            return appName;
        }
        return "unknown";
    }

    private static String getAppNameByProjectName() {
        return System.getProperty(PARAM_MARKING_PROJECT);
    }

}
