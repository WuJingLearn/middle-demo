package org.javaboy.utils.limiter;

/**
 * @author:majin.wj
 */
public class LeapBucketLimiter {

    // 桶的容量
    private final int capacity;
    // 漏出的速率
    private final int permitsPerSecond;
    // 剩余水量
    private long leftWater;
    // 上次注入水的时间
    private long timeStamp = System.currentTimeMillis();

    public LeapBucketLimiter(int capacity, int permitsPerSecond) {
        this.capacity = capacity;
        this.permitsPerSecond = permitsPerSecond;
    }


    public synchronized boolean tryAcquire() {
        // 计算剩余水量
        long now = System.currentTimeMillis();
        long timeGap = (now - timeStamp) / 1000;
        leftWater = Math.max(0, leftWater - timeGap * permitsPerSecond);
        timeStamp = now;
        // 如果未满，则放行；否则限流
        if (leftWater < capacity){
            leftWater += 1;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        long l2 = System.currentTimeMillis();
        System.out.println((l2-l1)/1000);
    }

}
