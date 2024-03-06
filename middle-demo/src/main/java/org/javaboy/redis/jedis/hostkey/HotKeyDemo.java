package org.javaboy.redis.jedis.hostkey;

import org.javaboy.redis.jedis.client.RedisClient;
import redis.clients.jedis.Jedis;

/**
 * @author majin.wj
 * @date 2023/6/20 11:01 AM
 * @desc
 */
public class HotKeyDemo {


    static Jedis jedis = RedisClient.jedis();

    /**
     * 解决热点key的问题。将key进行打散。
     *
     * @param args
     */
    public static void main(String[] args) {


        // 该key很火爆, 可以将key进行分离
        String content = jedis.get("news");
        System.out.println(content);

        set("news", "nba总决赛开始", 10);

        content = get("news", 10, 12343423424L);
        System.out.println(content);
        content = get("news", 10, 45431342436L);
        System.out.println(content);

    }

    static void set(String key, String content, int scale) {
        for (int i = 0; i < scale; i++) {
            String realKey = key + "_" + i;
            jedis.set(realKey, content);
        }
    }

    static String get(String key, int scale, long memberId) {
        long bucket = (memberId / 101) % scale;
        String realKey = key + "_" + bucket;
        return jedis.get(realKey);
    }

}
