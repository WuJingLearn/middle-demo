//package org.javaboy.utils.lock.rdb;
//
//import com.alibaba.boot.distributed.lock.ByteConverter;
//import com.aliyun.tair.tairstring.TairStringCluster;
//import com.aliyun.tair.tairstring.params.CasParams;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.params.SetParams;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by xianliang on 2/4/2020.
// *
// * @author xianliang
// * @date 2020/04/02
// */
//public class DefaultRedisService implements RedisService {
//
//    private static final String OK = "OK";
//
//    private final JedisCluster jedisCluster;
//    private final TairStringCluster tairStringCluster;
//
//    public DefaultRedisService(JedisCluster jedisCluster) {
//        if(jedisCluster == null) {
//            throw new RuntimeException("JedisCluster not exists");
//        }
//        this.jedisCluster = jedisCluster;
//        this.tairStringCluster = new TairStringCluster(jedisCluster);
//    }
//
//    @Override
//    public boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
//        int expireSeconds = (int)unit.toSeconds(timeout);
//        String result = jedisCluster.set(key, value, SetParams.setParams().nx().ex(expireSeconds));
//        //set失败时返回null
//        return OK.equals(result);
//    }
//
//    @Override
//    public boolean setIfAbsent(String key, Object value, ByteConverter converter, long timeout, TimeUnit unit) {
//        byte[] bytes = converter.serialize(value);
//        int expireSeconds = (int)unit.toSeconds(timeout);
//        String result = jedisCluster.set(serializeKey(key), bytes, SetParams.setParams().nx().ex(expireSeconds));
//        //set失败时返回null
//        return OK.equals(result);
//    }
//
//    @Override
//    public boolean renew(String key, String value, int expireSeconds) {
//        CasParams casParams = new CasParams();
//        casParams.ex(expireSeconds);
//        Long ret = tairStringCluster.cas(key, value, value, casParams);
//        if (1L == ret) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean cad(String key, String value) {
//        /**
//         * 1、key存在，值不匹配返回0
//         * 2、key存在，值匹配返回1
//         * 3、key不存在，返回-1
//         */
//        Long ret = tairStringCluster.cad(key, value);
//
//        if (1L == ret || ret == -1L) {
//            return true;
//        }
//
//        return false;
//    }
//
//    @Override
//    public Object get(String key, ByteConverter converter) {
//        return converter.deserialize(jedisCluster.get(serializeKey(key)));
//    }
//
//    private static byte[] serializeKey(String key) {
//        try {
//            return key.getBytes("UTF-8");
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Non-serializable object", e);
//        }
//    }
//}
