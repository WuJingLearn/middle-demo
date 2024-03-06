package org.javaboy.utils.asynccontext;

import com.taobao.eagleeye.EagleEye;
import com.taobao.eagleeye.RpcContext_inner;

/**
 * 首先，如果线程中的鹰眼上下文没有传递到子任务，子任务中无法获取到traceId，就导致一些节点traceId缺失。
 * <p>
 * 其次，异步子任务中新RPC调用开始时，如果发现之前没有traceId，就会生产新的traceId，也就导致了一个请求事务中traceId发生变化。
 * <p>
 * 最后，异步子任务执行完成后，又并没有清理鹰眼上下文，那么线程池中的常驻的线程就一直持有第一次RPC调用时生产的traceId。鹰眼上下文一直得不到更新，就会表现出traceId存活时间超长。
 * <p>
 * 在执行任务时，手动创建Runnable,将上下文设置进来。
 */
public class MyAsyncTask implements Runnable {

    private Object ctx;

    public void setContext(Object context) {
        this.ctx = context;
    }

    @Override
    public void run() {
        EagleEye.setRpcContext(ctx);  // 还原到 ThreadLocal
        try {
            System.out.println("traceId:" + EagleEye.getTraceId());
        } finally {
            EagleEye.clearRpcContext();
        }

    }

}
