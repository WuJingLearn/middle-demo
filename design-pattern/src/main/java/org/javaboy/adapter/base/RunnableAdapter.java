package org.javaboy.adapter.base;

import java.util.concurrent.Callable;

public class RunnableAdapter implements Callable {

    private Runnable runnable;
    private Object ret;

    public RunnableAdapter(Runnable runnable, Object ret) {
        this.runnable = runnable;
        this.ret = ret;
    }

    @Override
    public Object call() throws Exception {
        runnable.run();
        return ret;
    }
}
