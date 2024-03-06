package org.javaboy.redis.jedis.lock;

import java.util.concurrent.Callable;

/**
 * @author majin.wj
 * @date 2023/6/19 5:16 PM
 * @desc
 */
public class LockTemplate {


    public static <T> Result<T> execute(String lockName, Callable<T> callable) {
        try {
            T result = callable.call();
            return new Result<>();
        } catch (Exception e) {
            return new Result<>();
        }
    }


   /* public static InteractionResult execute(String key, int timeoutSeconds, Callable<InteractionResult> doInLock) {
        Lock lock = CommonTairLocker.newLock(key, timeoutSeconds);

        if (!lock.tryLock()) {
            ResultCode resultCode = ResultCode.TAIR_LOCK_ERROR;
            return InteractionResult.fail(resultCode.getCode(), resultCode.getMsg());
        }

        try {
            return doInLock.call();
        } catch (Exception e) {
            log.error("DO_IN_TAIR_LOCK_EXCEPTION, traceId: " + EagleEye.getTraceId(), e);
            ResultCode resultCode = ResultCode.TAIR_LOCK_ERROR;
            return InteractionResult.fail(resultCode.getCode(), resultCode.getMsg());
        } finally {
            lock.unlock();
        }
    }*/

}
