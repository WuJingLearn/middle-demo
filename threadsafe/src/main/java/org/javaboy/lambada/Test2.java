package org.javaboy.lambada;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方法引用
 */
public class Test2 {


    public static void main(String[] args) {
        List<String> strList = Arrays.asList("zss", "ls");
        List<Integer> collect = strList.stream().map(Test2::t1).collect(Collectors.toList());
        System.out.println(collect);

        Test2 test2 = new Test2();
        List<String> collect1 = strList.stream().map(test2::t2).collect(Collectors.toList());
        System.out.println(collect1);

        List<String> collect2 = strList.stream().filter(test2::t3).collect(Collectors.toList());
        System.out.println(collect2);
    }



    static Integer t1(String str) {
        return str.length();
    }


    String t2(String str) {
        return str.toUpperCase();
    }

    boolean t3(String str) {
        return str.length() >= 3;
    }
}
