//package org.javaboy.utils.lock.rdb;
//
//import com.alibaba.boot.distributed.lock.LockService;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by xianliang on 13/3/2020.
// *
// * @author xianliang
// * @date 2020/03/13
// */
//public class RdbLockService extends RdbLockSupport implements LockService {
//
//    public RdbLockService(RedisService redisService) {
//        super(redisService);
//    }
//
//    @Override
//    public boolean lock(String key, String value, long time, TimeUnit unit) {
//        long timeout = unit.toSeconds(time);
//        return occupy(key, value, (int)timeout);
//    }
//
//    @Override
//    public boolean unlock(String key, String value) {
//        return release(key, value);
//    }
//}
