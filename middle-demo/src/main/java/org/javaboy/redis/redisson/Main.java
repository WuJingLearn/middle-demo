package org.javaboy.redis.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RBucket;

/**
 * @author majin.wj
 * @date 2023/6/12 4:23 PM
 * @desc
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        RedissonClient client = Redisson.create();
        RBucket<String> bucket = client.getBucket("name");
        bucket.set("zs");
        System.out.println(bucket.get());
    }
}