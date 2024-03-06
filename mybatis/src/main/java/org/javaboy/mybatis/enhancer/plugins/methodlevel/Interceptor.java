package org.javaboy.mybatis.enhancer.plugins.methodlevel;

/**
 * @author majin.wj
 * @date 2023/6/9 2:36 PM
 * @desc
 */
public interface Interceptor {

    /**
     * 增强逻辑
     * invocation.proceed(): 传递给下个责任链
     *
     * @param invocation
     * @return
     */
    Object intercept(Invocation invocation) throws Throwable;

    default Object plugin(Object target) {
        return Plugin.wrap(this, target);
    }

}
