package org.javaboy.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {


    static class ProxyInvocation implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

    public  static Object getObject(Class<?> type) {
        return Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},new ProxyInvocation());
    }

    public static void main(String[] args) {
        Object object = ProxyFactory.getObject(RpcService.class);
    }

}
