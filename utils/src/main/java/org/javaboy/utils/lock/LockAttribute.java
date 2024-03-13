package org.javaboy.utils.lock;

/**
 * 锁的属性定义
 *
 * @author xianliang
 * @date 2017/03/11
 */
public interface LockAttribute {

    String key();

    int timeout();

    LockFailHandler failHandler();

    LockErrorHandler errorHandler();

    Class getReturnType();
}
