package org.javaboy.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedTest {
    public static void main(String[] args) {
        // 设置初始值和版本
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<Integer>(1,0);

        boolean success = reference.compareAndSet(1, 2, 0, 1);

        Integer reference1 = reference.getReference();


    }
}
