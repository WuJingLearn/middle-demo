package org.javaboy.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {
    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(10);
        atomicLong.compareAndSet(10, 11);
    }
}
