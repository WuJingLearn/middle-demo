package org.javaboy.scene.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author:majin.wj
 */
public class LruCache {


    private Map<String, Object> cache;
    private int size;

    public LruCache(int size) {
        this.size = size;
        this.cache = new LinkedHashMap(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if (cache.size() > size()) {
                    return true;
                }
                return false;
            }

            ;
        };
    }

    public static void main(String[] args) {
        int size = 3;
        LinkedHashMap<String, String> map = new LinkedHashMap(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() > 3;
            }
        };

        map.put("zs","1");
        map.put("ls","1");
        map.put("ws","1");
        map.put("ll","1");
        System.out.println(map);
    }


}
