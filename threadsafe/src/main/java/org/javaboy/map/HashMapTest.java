package org.javaboy.map;

import java.util.HashMap;

/**
 * HashMap默认初始化容量是16.负载因子0.75。当存储元素数量超过了容量*负载因子进行扩容；
 * 所以在设置初始化容量的时候，可以将容量设置成 存储个数/0.75 + 1. 这样就可以避免扩容；
 *
 *
 * HashMap在扩容时也是线程不安全的，每次扩容会扩容至原来数组的两倍。
 * hashMap直接创建一个新数组，然后将新数组赋值给table引用，然后将老数组中的数据，迁移过来；
 * 迁移过程不需要太理解。他不是通过将每个元素重新hash，而是另外一种算法。
 *
 *
 *
 * hashcode理论上是唯一的，但是存在自己重写hashcode情况；
 */
public class HashMapTest {
    public static void main(String[] args) {
        //存放4个元素，设置为8个元素，如果设置为4的化，会有一次扩容
        HashMap<String, String> map = new HashMap<>(8);

        map.put("zs","nan");
        map.put("ls","nan");
        map.put("ww","nan");
        map.put("kk","nan");

        int size = map.size();
        System.out.println(size);

        map.put(null,"三峡");

        System.out.println(map);

        System.out.println(map.get(null));

    }
}
