package org.javaboy.utils.lock;

/**
 * Created by xianliang on 18/2/13.
 *
 * @author xianliang
 * @date 2018/02/13
 */
public class DistributedLockException extends RuntimeException {

    public DistributedLockException(String message) {
        super(message);
    }
}
