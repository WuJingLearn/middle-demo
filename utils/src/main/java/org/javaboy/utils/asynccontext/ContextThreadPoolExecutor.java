package org.javaboy.utils.asynccontext;


import com.taobao.eagleeye.EagleEye;

import java.util.concurrent.*;

/**
 * 异步调用使用该线程池.透露上下文信息
 */
public class ContextThreadPoolExecutor extends ThreadPoolExecutor {

    public ContextThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable runnable) {
        ContextRunnable task = new ContextRunnable(runnable);
        task.setContext(EagleEye.currentRpcContext());
        super.execute(task);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new FutureTask<T>(runnable, value) {
            final Object rpcContext = EagleEye.currentRpcContext();

            @Override
            public void run() {
                try {
                    super.run();
                } finally {
                    EagleEye.clearRpcContext();
                }
            }
        };
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<T>(callable) {
            final Object rpcContext = EagleEye.currentRpcContext();

            public void run() {
                EagleEye.setRpcContext(rpcContext);
                try {
                    super.run();
                } finally {
                    EagleEye.clearRpcContext();
                }
            }
        };
    }
}

