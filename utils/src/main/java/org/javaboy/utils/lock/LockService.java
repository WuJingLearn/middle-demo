package org.javaboy.utils.lock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by xianliang on 17/11/28.
 *
 * @author xianliang
 * @date 2017/11/28
 */
public interface LockService {

    boolean lock(String key, String value, long time, TimeUnit unit);

    boolean unlock(String key, String value);

    default String assignLockValue() {
        return UUID.randomUUID().toString();
    }

}
