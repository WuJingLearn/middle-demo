package org.javaboy.mybatis.source.cache;


import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.FifoCache;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.impl.PerpetualCache;

/**
 *
 * 装饰器模式；
 *
 * Cache接口;
 * PerpetualCache 是具体提供功能的类，提供了具体缓存的能力。
 * 其他的FifoCache,LruCache是装饰器的角色
 *
 * 常用的缓存淘汰算法：
 * LRU,LFU,FIFO
 */
public class CacheExecutorDemo {

    public static void main(String[] args) {
        Cache perpetualCache = new PerpetualCache("test");
        Cache cache = new FifoCache(perpetualCache);
        cache.putObject("age",10);
        // 在mybatis的fifoCache中，通过LinkedList来维护先进先出的关系。
        // 但是在put数据的时候，没有在LinkedList校验是否已经存在的处理。主要是处于性能的考虑
        for (int i = 0; i < 1024; i++) {
            cache.putObject("name","zs");
        }
        Object object = cache.getObject("age");
        System.out.println(object);

    }
}
