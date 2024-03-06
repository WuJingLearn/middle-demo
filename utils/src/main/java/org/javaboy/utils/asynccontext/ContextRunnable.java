package org.javaboy.utils.asynccontext;

import com.taobao.eagleeye.EagleEye;

public class ContextRunnable implements Runnable {

    private Object context;
    private Runnable runnable;

    public ContextRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    @Override
    public void run() {

        try {
            EagleEye.setRpcContext(context);
            runnable.run();
            ;
        } finally {
            EagleEye.clearRpcContext();
        }
    }
}
