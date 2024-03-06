package org.javaboy.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LinkedHashMap的实现原理：
 * LinkedHashMap中的Entry继承了HashMap的Node，在Entry中包含了before和after指针，
 * 分别指向当前 Entry 前后的两个 Entry 节点；在LinkedHashMap中，还维护了head和tail两个节点，
 * 分别指向第一个和最后一个Entry节点。
 * 在put元素之后，有一个removeEldestEntry方法，默认返回false.用来判断什么条件下移除Map中的元素，
 * 当使用LinkedHashMap来实现LRU缓存的时候，重写removeEldsEntry方法，当size() > 设定的阈值的时候，进行淘汰；
 * 将head指向的元素删除。
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 2;
            }
        };

        map.put("zs", "10");
        map.put("ls", "20");
        /**
         * 在访问这个元素之后，将这个元素放到队列的尾部。
         * 并将这个元素删除。因为这个元素是一个双链表，可以直接通过before指针拿到前面的元素
         */
        map.get("zs");
        map.put("ww", "1");


        /**
         * 从头元素，开始访问，移除也是从头元素开始
         */
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }


    }




}
