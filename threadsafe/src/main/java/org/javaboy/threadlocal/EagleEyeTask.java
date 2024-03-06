package org.javaboy.threadlocal;

import java.util.concurrent.*;

/**
 * @author:majin.wj
 * EagleEye基于ThreadLocal实现上下文的存储，所以当业务方使用同步的方式时对使用者透明，但是该方式无法支持异步线程的场景，所以在使用异步线程时
 * 需要手动传递上下文，当业务逻辑转移到异步线程时，需要先备份 EagleEye 的调用上下文到异步任务中，保证链路的正确性。
 *
 * 修改方案一（推荐）
 * 提交异步线程前，需要做 EagleEye 上下文备份
 * Object ctx = EagleEye.getRpcContext(); // 从当前 ThreadLocal 备份
 * MyAsyncTask task = new MyAsyncTask();  // 这里的 MyAsyncTask 是一个业务自定义的 Runnable
 * task.setRpcContext(ctx); // 将 ctx 保存到 task 中
 * Future future = bizThreadPoolExecutor.submit(task); // 提交任务
 * // 后面继续执行其他逻辑，或者用 future.get() 等待任务的结果，都没有问题
 * // 如果 submit 多个 task，每个 task 都需要保存一份 ctx
 */
public class EagleEyeTask {

    class MyAsyncTask implements Runnable {
        private Object ctx; // 用于存放之前保存的 EagleEye 上下文

        public void setRpcContext(Object ctx) { this.ctx = ctx; }

        public void run() {
           // EagleEye.setRpcContext(ctx);  // 还原到 ThreadLocal
            try {
                // 开始做异步逻辑，如调用 HSF、Notify、Tair 之类
                // ...
            } finally {
             //   EagleEye.clearRpcContext();  // 务必清理 ThreadLocal 的上下文，避免异步线程复用时出现上下文互串的问题
            }
        }
    }

    /**
     * 修改方案二
     * 如果并发执行的线程池是业务自己创建的，可以用方案二。只需要覆盖 ThreadPoolExecutor 里面的两个方法即可。(注意: 只针对使用 submit 方法,
     * 如果想使用 execute方法, 请参考方案一) 通过 fix ThreadPoolExecutor 实现，这也是 Ali JDK 的 EagleEye patch 实现原理
     */

    class EagleEyeFixThreadPoolExecutor extends ThreadPoolExecutor {

        public EagleEyeFixThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
            return new FutureTask<T>(runnable, value) {
              /*  final Object rpcContext = EagleEye.currentRpcContext();
                public void run() {
                    EagleEye.setRpcContext(rpcContext);
                    try {
                        super.run();
                    } finally {
                        EagleEye.clearRpcContext();
                    }
                }*/
            };
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
            return new FutureTask<T>(callable) {
              /*  final Object rpcContext = EagleEye.currentRpcContext();
                public void run() {
                    EagleEye.setRpcContext(rpcContext);
                    try {
                        super.run();
                    } finally {
                        EagleEye.clearRpcContext();
                    }
                }*/
            };
        }
    }


}
