package org.javaboy.redis.leaderboard.demo;

import org.javaboy.redis.leaderboard.*;

import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/20 3:46 PM
 * @desc
 */
public class Test {

    public static void main(String[] args) {

        LeaderBoardConfig leaderBoardConfig = new LeaderBoardConfig();
        leaderBoardConfig.setLevel(0);
        leaderBoardConfig.setScale(2);
        leaderBoardConfig.setMaxBucket(4);

        LeaderBoardConfigUtil.putConfig("xxleEnergyPk", leaderBoardConfig);

        LeaderBoardService leaderBoardService = new LeaderBoardServiceImpl();

        leaderBoardService.enterLeaderboard("xxleEnergyPk-2022", 234L, 1111L);
        leaderBoardService.enterLeaderboard("xxleEnergyPk-2022", 123L, 10L);
        leaderBoardService.enterLeaderboard("xxleEnergyPk-2022", 111L, 15L);


        List<Member> members = leaderBoardService.queryTop("xxleEnergyPk-2022", 10);
        System.out.println(members);


    }
}
