package org.javaboy.redis.jedis;

import org.javaboy.redis.jedis.client.RedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

/**
 * @author majin.wj
 * @date 2023/6/19 4:59 PM
 * @desc
 */
public class ZsetDemo {

    static Jedis jedis = RedisClient.jedis();

    public static void main(String[] args) {


    }

    public static void testMultiValueLeaderBoard() {

        multiValueLeaderBoard(1, 2, "zs", "2022");
        multiValueLeaderBoard(1, 3, "ls", "2022");
        Set<Tuple> tuples = jedis.zrevrangeWithScores("2022", 0, -1);
        System.out.println(tuples);
    }

    /**
     * 多维度排行榜，
     * 金牌数量一样，按照银牌排序
     */
    public static void multiValueLeaderBoard(int gold, int money, String memberId, String bid) {
        double score = Double.valueOf(gold + "." + money);
        jedis.zadd(bid, score, memberId);
    }

    public static void testApi() {
        Jedis jedis = RedisClient.jedis();

        jedis.zadd("leaderboard1", 10d, "zs");
        jedis.zadd("leaderboard1", 11d, "ls");

        Set<Tuple> leaderboard1 = jedis.zrangeWithScores("leaderboard1", 0, 1000);

        System.out.println(leaderboard1);

        //删除分数最小的
        jedis.zremrangeByRank("leaderboard1", 0, 0);

        leaderboard1 = jedis.zrangeWithScores("leaderboard1", 0, 1000);
        System.out.println(leaderboard1);

    }

    /**
     * 当前分数
     *
     * @param memberId
     * @param score
     * @param limitSize
     */
    public void addScore(String leaderboard, long memberId, double score, int limitSize) {

        jedis.zadd(leaderboard, score, String.valueOf(memberId));
        long count = jedis.zcard(leaderboard);
        if (count > limitSize) {
            jedis.zremrangeByRank(leaderboard, 0, 0);
        }
    }
}
