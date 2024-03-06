package org.javaboy.redis.leaderboard;

/**
 * @author majin.wj
 * @date 2023/6/20 2:29 PM
 * @desc
 */
public class Member {

    private Long id;
    private int rank;
    private long score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", rank=" + rank +
                ", score=" + score +
                '}';
    }
}
