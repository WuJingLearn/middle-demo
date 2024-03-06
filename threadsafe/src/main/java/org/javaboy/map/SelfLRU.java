package org.javaboy.map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class SelfLRU {

    /**
     * 维护缓存
     */
    private Map<String, String> cache = new HashMap<>();

    private LinkedList<String> list = new LinkedList<>();

    int size = 2;

    /**
     * 我的这个实现不太好，因为在移动元素的时候，在删除linkedList中的元素时，还需要先for找到他，这太慢了。
     *
     * @param key
     * @param value
     */
    void put(String key, String value) {
        String oldValue = cache.put(key, value);
        if (cache.size() > size) {
            String removeKey = list.removeFirst();
            cache.remove(removeKey);
        }
        if (oldValue != null) {
            list.remove(key);
        }
        list.addLast(key);
    }

    String get(String key) {
        String value = cache.get(key);
        if (value == null) {
            return null;
        }
        list.remove(key);
        list.addLast(key);
        return value;
    }

    void print() {
        Set<Map.Entry<String, String>> entry = cache.entrySet();
        for (Map.Entry<String, String> stringStringEntry : entry) {
            System.out.println(stringStringEntry.getKey() + ":" + stringStringEntry.getValue());
        }
        System.out.println("----- print ---");
        for (String key : list) {
            System.out.println("key:" + key);
        }
    }

    public static void main(String[] args) {
        SelfLRU selfLRU = new SelfLRU();
        selfLRU.put("zs", "11");
        selfLRU.put("ls", "11");
        selfLRU.get("zs");

        selfLRU.put("ww", "11");
        selfLRU.print();

    }
}