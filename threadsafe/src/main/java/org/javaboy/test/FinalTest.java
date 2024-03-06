package org.javaboy.test;

public class FinalTest {

    public static void main(String[] args) {
        final Object o = new Object();
        // 无法改变引用
//       o = new Object();

        String a = "a";
        a = "c";

        final String v = "x";
        // 无法修改引用
//        v = "a";
    }

}
