package org.javaboy.redis.leaderboard;

import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/20 2:13 PM
 * @desc
 */
public interface LeaderBoardService {


    List<Member> queryTop(String bid, int top);

    void enterLeaderboard(String bid, Long memberId, Long score);


    void enterLeaderboardLimitSize(String bid, Long memberId, Long score, int limitSize);


}
