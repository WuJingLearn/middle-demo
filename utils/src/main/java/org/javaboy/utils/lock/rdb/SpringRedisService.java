//package org.javaboy.utils.lock.rdb;
//
//import com.alibaba.boot.distributed.lock.ByteConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by xianliang on 2/4/2020.
// *
// * @author xianliang
// * @date 2020/04/02
// */
//public class SpringRedisService implements RedisService {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        Boolean result = operations.setIfAbsent(key, value, timeout, unit);
//        return Boolean.TRUE.equals(result);
//    }
//
//    @Override
//    public boolean setIfAbsent(String key, Object value, ByteConverter converter, long timeout, TimeUnit unit) {
//        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//        Boolean result = operations.setIfAbsent(key, value, timeout, unit);
//        return Boolean.TRUE.equals(result);
//    }
//
//    @Override
//    public boolean renew(String key, String value, int expireSeconds) {
//        throw new UnsupportedOperationException("SpringRedisService.renew");
//    }
//
//    @Override
//    public boolean cad(String key, String value) {
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        String current = operations.get(key);
//        if(current != null && current.equals(value)) {
//            Boolean result = stringRedisTemplate.delete(key);
//            return Boolean.TRUE.equals(result);
//        }
//        return current == null;
//    }
//
//    @Override
//    public Object get(String key, ByteConverter converter) {
//        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//        return operations.get(key);
//    }
//}
