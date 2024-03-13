package org.javaboy.utils.lock;

/**
 * Created by xianliang on 17/3/13.
 *
 * @author xianliang
 * @date 2017/03/13
 */
public enum LockPolicy {
    /**
     * 适用于锁冲突频繁的场景
     */
    NORMAL,
    /**
     * 适用于锁冲突不频繁的场景，如超过50%都不会出现锁冲突
     */
    FAST;
}
