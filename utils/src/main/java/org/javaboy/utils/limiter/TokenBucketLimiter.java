package org.javaboy.utils.limiter;

/**
 * @author:majin.wj
 * 令拍桶
 */
public class TokenBucketLimiter {
    // 令牌桶的容量
    private final long capacity;
    // 令牌发放速率
    private final long generatedPerSecond;
    // 最后一个令牌发放的时间
    private long lastTokenTime = System.currentTimeMillis();
    // 当前令牌数量
    private long currentTokens;

    public TokenBucketLimiter(long capacity, long generatedPerSecond) {
        this.capacity = capacity;
        this.generatedPerSecond = generatedPerSecond;
    }

    public synchronized boolean tryAcquire() {
        /*
          计算当前令牌的数量
          请求时间在最后令牌产生的时间相差大于等于1s
          1.重新计算令牌桶中的令牌数量
          2. 将最后一个令牌发放时间重置为当前时间
         */
        long now = System.currentTimeMillis();
        if (now - lastTokenTime >= 1000) {
            long newPermits = (now - lastTokenTime) / 1000 * generatedPerSecond;
            currentTokens = Math.min(currentTokens + newPermits, capacity);
            lastTokenTime = now;
        }
        if (currentTokens > 0) {
            currentTokens--;
            return true;
        }
        return false;
    }
}
