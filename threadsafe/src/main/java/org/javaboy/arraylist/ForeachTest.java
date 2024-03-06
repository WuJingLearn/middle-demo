package org.javaboy.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author:majin.wj
 */
public class ForeachTest {

    public static void main(String[] args) {

        test1();

    }

    private static void test3() {
        List<String> list  = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            if(next.equals("2")) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    /**
     * 报错
     */
    private static void test2() {
        List<String> list  = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");
        // 增强for循环,本质上是foreach循环
        for (String s : list) {
            if(s.equals("2")){
                list.remove(s);
            }
        }
    }

    /**
     * 第2个2无法删除
     */
    private static void test1() {
        List<String> list  = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).equals("2")){
                list.remove(i);
                i--;
            }
        }
        System.out.println(list);
    }


}

/**
 *
 * who,when.where
 * what,why,
 *
 * how,
 *
 */