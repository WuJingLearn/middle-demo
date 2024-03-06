package org.javaboy.priorityQueue;

import java.util.*;

/**
 * @author:majin.wj
 */
public class Test1 {

    /**
     * 经过测试，使用大顶堆和使用数组排序的方式，
     * 数组排序的效率差不多
     * @param args
     */
    public static void main(String[] args) {

        Random random = new Random();


        List<Integer> bucket1 = new ArrayList<>();
        List<Integer> bucket2 = new ArrayList<>();
        List<Integer> bucket3 = new ArrayList<>();
        List<Integer> bucket4 = new ArrayList<>();
        List<Integer> bucket5 = new ArrayList<>();
        List<Integer> bucket6 = new ArrayList<>();
        List<Integer> bucket7 = new ArrayList<>();
        List<Integer> bucket8 = new ArrayList<>();
        List<Integer> bucket9 = new ArrayList<>();
        List<Integer> bucket10 = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            bucket1.add(random.nextInt(10000));
            bucket2.add(random.nextInt(10000));
            bucket3.add(random.nextInt(10000));
            bucket4.add(random.nextInt(10000));
            bucket5.add(random.nextInt(10000));
            bucket6.add(random.nextInt(10000));
            bucket7.add(random.nextInt(10000));
            bucket8.add(random.nextInt(10000));
            bucket9.add(random.nextInt(10000));
            bucket10.add(random.nextInt(10000));
        }

        long start = System.currentTimeMillis();
        PriorityQueue<Integer> queue = new PriorityQueue<>(2500, Comparator.reverseOrder());
        queue.addAll(bucket1);
        queue.addAll(bucket2);
        queue.addAll(bucket3);
        queue.addAll(bucket4);
        queue.addAll(bucket5);
        queue.addAll(bucket6);
        queue.addAll(bucket7);
        queue.addAll(bucket8);
        queue.addAll(bucket9);
        queue.addAll(bucket10);

        List<Integer> result = new ArrayList<>(500);
        for (int i = 0; i < 500; i++) {
            result.add(queue.poll());
        }
        //9ms
        System.out.println("使用优先队列耗时:" + (System.currentTimeMillis() - start));
        System.out.println(result);


        long start2 = System.currentTimeMillis();

        List<Integer> all = new ArrayList<>(2500);
        all.addAll(bucket1);
        all.addAll(bucket2);
        all.addAll(bucket3);
        all.addAll(bucket4);
        all.addAll(bucket5);
        all.addAll(bucket6);
        all.addAll(bucket7);
        all.addAll(bucket8);
        all.addAll(bucket9);
        all.addAll(bucket10);
        all.sort(Comparator.reverseOrder());
        List<Integer> result2 = new ArrayList<>(500);
        for (int i = 0; i < 500; i++) {
            result2.add(all.get(i));
        }
        System.out.println("使用排序耗时:" + (System.currentTimeMillis() - start2));
        System.out.println(result2);
    }


}
