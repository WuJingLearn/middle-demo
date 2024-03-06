package org.javaboy.redis.leaderboard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author majin.wj
 * @date 2023/6/20 1:55 PM
 * @desc
 */
public abstract class KeyHashing {

    /**
     * 根据hash算法,得出uid所在的具体key
     *
     * @param uid
     * @return
     */
    public abstract String hashUid(Long uid);

    /**
     * 所有的具体的key
     *
     * @return
     */
    public abstract List<String> possibleKeys();

    public static KeyHashing newBucket(String prefixKey, int maxBuckets, int scale) {
        return new BucketHashStrategy(prefixKey, maxBuckets, scale);
    }


    static class BucketHashStrategy extends KeyHashing {

        private String prefixKey;
        private int maxBuckets;
        private int scale;
        private int buckets;

        private BucketHashStrategy(String prefixKey, int maxBuckets, int scale) {
            this.prefixKey = prefixKey;
            this.maxBuckets = maxBuckets;
            this.scale = scale;
            this.buckets = initBuckets();
        }

        private int initBuckets() {
            if (scale == 0) {
                return maxBuckets;
            } else {
                return (int) Math.ceil(maxBuckets * 1.0 / scale);
            }
        }

        @Override
        public String hashUid(Long uid) {
            int bucket = (int) (uid / 10) % buckets;
            return String.format("%s_%s", prefixKey, bucket);
        }

        @Override
        public List<String> possibleKeys() {
            return IntStream.range(0, buckets)
                    .mapToObj(bucket -> String.format("%s_%s", prefixKey, bucket))
                    .collect(Collectors.toList());
        }
    }
}
