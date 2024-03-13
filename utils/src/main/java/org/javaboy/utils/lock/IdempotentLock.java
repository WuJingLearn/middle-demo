package org.javaboy.utils.lock;

import java.lang.annotation.*;

/**
 * Created by xianliang on 2019-08-24.
 *
 * @author xianliang
 * @date 2019/08/24
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdempotentLock {

    /**
     * 代表一个共享资源的锁，不同资源的key不能相同
     *
     * @return
     */
    String key();

    /**
     * 锁的超时时间，即锁的有效时间，默认1分钟，业务操作必须在超时时间内完成，否则锁将自动被释放。
     * @return 超时时间，以秒为单位
     */
    int timeout() default 60;

    /**
     * 幂等生命周期
     * @return
     */
    int lifecycle() default 5 * 24 * 60 * 60;

    /**
     * 是否忽略异常
     * @return
     */
    boolean ignoreException() default false;

    Class<? extends ByteConverter> converterType() default DefaultByteConverter.class;

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
