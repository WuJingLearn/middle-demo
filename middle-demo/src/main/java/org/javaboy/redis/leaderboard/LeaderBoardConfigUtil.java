package org.javaboy.redis.leaderboard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author majin.wj
 * @date 2023/6/20 2:48 PM
 * @desc 将所有排行榜的配置都统一管理起来
 */
public class LeaderBoardConfigUtil {

    /**
     * 玩法类型:排行榜配置
     */
    private static Map<String, LeaderBoardConfig> configMap = new ConcurrentHashMap<>();


    public static void putConfig(String play, LeaderBoardConfig config) {
        configMap.put(play, config);
    }


    public static LeaderBoardConfig leaderBoardConfig(String bid) {
        String leaderBoardPlay = getLeaderBoardPlay(bid);
        return configMap.get(leaderBoardPlay);
    }

    private static String getLeaderBoardPlay(String bid) {
        if (bid == null) {
            throw new IllegalArgumentException();
        }
        String[] split = bid.split("-");
        if (split.length < 2) {
            throw new IllegalArgumentException();
        }
        return split[0];
    }

}
