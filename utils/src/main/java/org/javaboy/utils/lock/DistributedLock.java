package org.javaboy.utils.lock;

import java.lang.annotation.*;

/**
 * 定义分布式锁的属性
 *
 * @author xianliang
 * @date 2017/03/07
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

    /**
     * 代表一个共享资源的锁，不同资源的key不能相同
     *
     * @return
     */
    String key();

    /**
     * 锁的超时时间，即锁的有效时间，默认1分钟，业务操作必须在超时时间内完成，否则锁将自动被释放。
     * 注意这个超时时间需要设置一个合理的值，设置过短可能导致业务操作还没完成，锁就被释放了，设置过长（当出了现网络分区或客户端崩溃后）可能导致其他业务操作在较长时间内获取不到锁。
     *
     * @return 超时时间，以秒为单位
     */
    int timeout() default 60;

    /**
     * 加锁失败处理器定义
     *
     * @return
     */
    Class<? extends LockFailHandler> failHandlerClass() default SimpleLockFailHandler.class;

    /**
     * 系统错误处理器定义
     *
     * @return
     */
    Class<? extends LockErrorHandler> errorHandlerClass() default SimpleLockFailHandler.class;
}
