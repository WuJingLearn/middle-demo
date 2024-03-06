package org.javaboy.comparator;

import java.util.Comparator;

public class Demo1 {


    public static void main(String[] args) {


        Comparator<Integer> reverseOrder = Comparator.reverseOrder();


        int compare = reverseOrder.compare(2, 1);
        System.out.println(compare);

        int parent = (2 - 1) >>> 1;
        System.out.println(parent);


    }

}
