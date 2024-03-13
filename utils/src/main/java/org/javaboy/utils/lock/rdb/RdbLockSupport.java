//package org.javaboy.utils.lock.rdb;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by xianliang on 13/3/2020.
// * @link https://yuque.antfin-inc.com/tair-userdoc/cloud-redis/distlock
// * @author xianliang
// * @date 2020/03/13
// */
//public class RdbLockSupport {
//
//    private final RedisService redisService;
//
//    public RdbLockSupport(RedisService redisService) {
//        this.redisService = redisService;
//    }
//
//    protected boolean occupy(String key, String requestId, int expireSeconds) {
//        checkArgument(requestId != null && key != null);
//        return redisService.setIfAbsent(key, requestId, expireSeconds, TimeUnit.SECONDS);
//    }
//
//    protected boolean release(String key, String requestId) {
//        checkArgument(requestId != null && key != null);
//        return redisService.cad(key, requestId);
//    }
//
//    public static void checkArgument(boolean expression) {
//        if (!expression) {
//            throw new IllegalArgumentException();
//        }
//    }
//}
