//package org.javaboy.utils.lock.rdb;
//
//import com.alibaba.boot.distributed.lock.ByteConverter;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by xianliang on 2/4/2020.
// *
// * @author xianliang
// * @date 2020/04/02
// */
//public interface RedisService {
//
//    boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit);
//
//    boolean setIfAbsent(String key, Object value, ByteConverter converter, long timeout, TimeUnit unit);
//
//    boolean renew(String key, String value, int expireSeconds);
//
//    boolean cad(String key, String value);
//
//    Object get(String key, ByteConverter converter);
//}
