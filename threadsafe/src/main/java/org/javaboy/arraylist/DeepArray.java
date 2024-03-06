package org.javaboy.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:majin.wj
 */
public class DeepArray {

    static class Obj {

    }
    public static void main(String[] args) {
        List<Obj> list1 = new ArrayList<>();
        list1.add(new Obj());



        List<Obj> list2 = new ArrayList<>(list1);

        // 这里是同一个元素
        System.out.println(list2.get(0));
        System.out.println(list1.get(0));
    }

}
