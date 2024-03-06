package org.javaboy.redis.leaderboard;

import org.javaboy.redis.jedis.client.RedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;

/**
 * @author majin.wj
 * @date 2023/6/20 2:47 PM
 * @desc
 */
public class LeaderBoardServiceImpl implements LeaderBoardService {


    Jedis jedis = RedisClient.jedis();

    /**
     * 查询排行榜前N名,从最上层桶查询，因为最上层桶的桶数量最少。
     *
     * @param bid
     * @param top
     * @return
     */
    @Override
    public List<Member> queryTop(String bid, int top) {
        //1.获取排行榜配置
        LeaderBoardConfig leaderBoardConfig = LeaderBoardConfigUtil.leaderBoardConfig(bid);
        if (leaderBoardConfig == null) {
            return Collections.emptyList();
        }
        int level = leaderBoardConfig.getLevel();
        // 最上层的榜单id
        String keyPrefix = String.format("%s_l%s", bid, level);
        // 最上层的桶数量，是第一层数量/scale
        int scale = (int) Math.pow(leaderBoardConfig.getScale(), level);
        KeyHashing keyHashing = KeyHashing.newBucket(keyPrefix, leaderBoardConfig.getMaxBucket(), scale);
        // 最上层桶所有key
        List<String> possibleKeys = keyHashing.possibleKeys();

        PriorityQueue<Tuple> priorityQueue = new PriorityQueue(Comparator.reverseOrder());
        for (String key : possibleKeys) {
            Set<Tuple> members = jedis.zrangeWithScores(key, 0, top);
            for (Tuple member : members) {
                priorityQueue.add(member);
            }
        }
        List<Member> topMembers = new ArrayList<>();
        for (int i = 0; i < top; i++) {
            Tuple tuple = priorityQueue.poll();
            if (tuple == null) {
                break;
            }
            double score = tuple.getScore();
            Long memberId = Long.valueOf(tuple.getElement());
            Member member = new Member();
            member.setId(memberId);
            member.setRank(i + 1);
            member.setScore(Double.valueOf(score).longValue());
            topMembers.add(member);
        }
        return topMembers;
    }


    @Override
    public void enterLeaderboard(String bid, Long memberId, Long score) {
        LeaderBoardConfig leaderBoardConfig = LeaderBoardConfigUtil.leaderBoardConfig(bid);
        if (leaderBoardConfig == null) {
            throw new RuntimeException("Failed to enterLeaderBoard, please create leaderboard config first");
        }
        // 层级
        int level = leaderBoardConfig.getLevel();
        int maxBucket = leaderBoardConfig.getMaxBucket();
        int scale = leaderBoardConfig.getScale();
        for (int i = 0; i <= level; i++) {
            String prefix = String.format("%s_l%s", bid, i);
            KeyHashing keyHashing = KeyHashing.newBucket(prefix, maxBucket, (int) Math.pow(scale, i));
            String key = keyHashing.hashUid(memberId);
            // 加入榜单
            jedis.zadd(key, score, String.valueOf(memberId));
        }
    }

    /**
     * 排行榜上只保存上榜的成员，当排行榜上成员数已满。当有新成员加入时，会将排名最小的成员移除掉。
     *
     * @param bid
     * @param memberId
     * @param score
     * @param limitSize
     */
    @Override
    public void enterLeaderboardLimitSize(String bid, Long memberId, Long score, int limitSize) {
        LeaderBoardConfig leaderBoardConfig = LeaderBoardConfigUtil.leaderBoardConfig(bid);
        if (leaderBoardConfig == null) {
            throw new RuntimeException("Failed to enterLeaderBoard, please create leaderboard config first");
        }
        // 层级
        int level = leaderBoardConfig.getLevel();
        int maxBucket = leaderBoardConfig.getMaxBucket();
        int scale = leaderBoardConfig.getScale();
        for (int i = 0; i <= level; i++) {
            String prefix = String.format("%s_l%s", bid, i);

            KeyHashing keyHashing = KeyHashing.newBucket(prefix, maxBucket, (int) Math.pow(scale, i));
            String key = keyHashing.hashUid(memberId);
            Double preScore = jedis.zscore(key, String.valueOf(memberId));
            if (preScore != null && preScore.longValue() == score) {
                break;
            }
            // 加入榜单
            jedis.zadd(key, score, String.valueOf(memberId));
            long size = jedis.zcard(key);
            if (size > limitSize) {
                // 移除排行最低的成员
                jedis.zremrangeByRank(key, 0, 0);
            }
        }
    }
}
