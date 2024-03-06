package org.javaboy.gc.phantomreference;

import sun.misc.Cleaner;

public class Test {

    private Cleaner cleaner;

    /**
     * Test对象被回收时触发
     */
    private Runnable runnable = () -> {

    };

    public Test() {
        this.cleaner = Cleaner.create(this, runnable);
    }


}
