package org.javaboy.utils.limiter;

/**
 * @author:majin.wj 参考文章：https://blog.csdn.net/weixin_44991304/article/details/126527087
 *
 * 固定时间窗口限流： 固定时间窗口限流存在窗口边界限流不准确的问题，
 * 假设qps是10，在第一个窗口的后100毫秒访问了9次，不会出现限流的，然后在后一个窗口的前100ms又访问了9次。
 * 此时也是不会限流的。但是在1s的这样的一个时间单位中，系统却被访问了18次；
 */
public class CounterLimiter {

    // 每秒限制的请求数
    private final long permitsPerSecond;

    // 上一个窗口开始的时间
    private long timestamp = System.currentTimeMillis();

    // 计数器
    private int counter;

    public CounterLimiter(long permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        // 窗口内请求数量小于阈值，更新计数放行，否则拒绝请求
        if (now - timestamp < 1000) {
            if (counter < permitsPerSecond) {
                counter++;
                return true;
            } else {
                return false;
            }
        }
        // 时间窗口过期，重置计数器和时间戳
        counter = 0;
        timestamp = now;
        return true;
    }

    public synchronized boolean acquire() {
        long now = System.currentTimeMillis();
        // 还在1s范围内
        if (now - timestamp < 1000) {
            return counter++ < permitsPerSecond;
        }
        timestamp = now;
        counter = 0;
        return true;
    }

}
