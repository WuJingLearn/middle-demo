package org.javaboy.middleware.impl1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * @author majin.wj
 * @date 2023/5/9 4:38 PM
 * @desc
 */
public class ConnectionProxy implements InvocationHandler {

    private Connection realConnection;
    private FastDataSource fastDataSource;

    public ConnectionProxy(Connection realConnection, FastDataSource fastDataSource) {
        this.realConnection = realConnection;
        this.fastDataSource = fastDataSource;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("commit".equals(method.getName())){
            fastDataSource.recycleConnection(realConnection);
            if(realConnection.getAutoCommit()) {
                return null;
            }
        }
        return method.invoke(realConnection, args);
    }
}
