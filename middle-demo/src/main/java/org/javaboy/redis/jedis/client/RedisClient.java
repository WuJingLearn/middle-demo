package org.javaboy.redis.jedis.client;

import redis.clients.jedis.Jedis;

/**
 * @author majin.wj
 * @date 2023/6/19 5:00 PM
 * @desc
 */
public class RedisClient {


    public static Jedis jedis(){
        return new Jedis();
    }
}
