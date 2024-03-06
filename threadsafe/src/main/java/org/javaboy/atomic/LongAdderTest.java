package org.javaboy.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author majin.wj
 * @date 2023/6/26 8:44 PM
 * @desc
 */
public class LongAdderTest {

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();

        longAdder.longValue();

        longAdder.decrement();;
    }
}
