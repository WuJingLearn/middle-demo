package org.javaboy.redis.leaderboard;

/**
     * 排行榜配置
     */
public class LeaderBoardConfig {
        /**
         * 最大桶，通常就是第一层桶的数量
         */
        private int maxBucket;
        /**
         * 分层的数量.level从0开始,第一层就是0
         */
        private int level;

        /**
         * 上面的层按照比例，比下面一层少的规模
         * 比如scale是2，第一层如果10个桶，那么第二层就是10/2=5个桶
         */
        private int scale;

        /**
         * 排行榜保存时间
         */
        private long saveTime;

        public int getMaxBucket() {
            return maxBucket;
        }

        public void setMaxBucket(int maxBucket) {
            this.maxBucket = maxBucket;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getScale() {
            return scale;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }
    }