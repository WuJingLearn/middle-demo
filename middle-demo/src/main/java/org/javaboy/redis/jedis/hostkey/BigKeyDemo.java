package org.javaboy.redis.jedis.hostkey;

import org.javaboy.redis.jedis.client.RedisClient;
import redis.clients.jedis.Jedis;

/**
 * @author majin.wj
 * @date 2023/6/20 11:03 AM
 * @desc
 * 我们这边的系统，会往Redis里存一个大Key（value值大小有400MB多），Redis是集群版，8GB 8节点的，
 * 实际存储时只会将这个key放到一个节点中，这样这一个节点成为了整个系统的瓶颈。而且扩容也无法解决该问题。
 */
public class BigKeyDemo {

    static Jedis jedis = RedisClient.jedis();

    public static void main(String[] args) {


        /**
         *  解决大key的问题，比如list中存放全国的人口，那么这个list中会有很多内容。数据很大。
         *
         *  通过将人口按照省份的维度进行划分, 通过34个key来存储每个省份的人数
         *
         */
        jedis.lpush("people", "张三", "李四");
        long people = jedis.llen("people");
        System.out.println(people);

        jedis.lpush("anhuiPeople", "张三");
        jedis.lpush("zhejiangPeople", "李四");
        long anhuiPeople = jedis.llen("anhuiPeople");
        long zhejiangPeople = jedis.llen("zhejiangPeople");
        System.out.println(zhejiangPeople + anhuiPeople);


    }

}
