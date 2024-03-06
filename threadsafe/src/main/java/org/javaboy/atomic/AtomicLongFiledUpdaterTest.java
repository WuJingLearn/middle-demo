package org.javaboy.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class AtomicLongFiledUpdaterTest {


    static class Num{
       volatile long num;

       volatile String name;

        public long getNum() {
            return num;
        }

        public void setNum(long num) {
            this.num = num;
        }
    }
    public static void main(String[] args) {

        Num num = new Num();
        /**
         * 为业务提供了
         */
        AtomicLongFieldUpdater<Num> updater = AtomicLongFieldUpdater.newUpdater(Num.class, "num");
        boolean success = updater.compareAndSet(num, 0, 1);
        System.out.println(success);



    }
}
