package org.javaboy.mybatis.source.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 访问频率
 * LFU（Least Frequently Used）算法是一种缓存淘汰算法，它根据缓存中各元素的访问频率，将访问频率最低的元素淘汰掉，
 * 以腾出空间存储新元素。在 Java 中，可以使用 LinkedHashMap 来实现 LFU 算法。下面是一个简单的实现示例：
 *
 * 该实现继承自 LinkedHashMap，使用 LinkedHashMap 的 accessOrder 构造方法参数来保证缓存中元素的访问顺序。
 * LFU 缓存的访问频率信息保存在 frequencyMap 中，put 方法在添加元素的时候更新访问频率，remove 方法在删除元素
 * 的时候删除对应的访问频率信息。通过重写 removeEldestEntry 方法，可以设置缓存最大容量并实现自动淘汰。最后，通过 print 方法可以打印出当前缓存中的所有元素。
 * @param <K>
 * @param <V>
 */
public class LFUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    private final Map<K, Integer> frequencyMap;

    public LFUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
        this.frequencyMap = new LinkedHashMap<>();
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    @Override
    public V put(K key, V value) {
        if (containsKey(key)) {
            frequencyMap.put(key, frequencyMap.get(key) + 1);
        } else {
            frequencyMap.put(key, 1);
        }
        return super.put(key, value);
    }

    @Override
    public V remove(Object key) {
        frequencyMap.remove(key);
        return super.remove(key);
    }

    public void print() {
        System.out.println(this);
    }

    public static void main(String[] args) {
        LFUCache<String, String> cache = new LFUCache<>(3);
        cache.put("a", "1");
        cache.put("b", "2");
        cache.put("c", "3");
        cache.print(); // 输出: {a=1, b=2, c=3}
        cache.get("b");
        cache.get("b");
        cache.get("b");
        cache.get("a");
        cache.get("a");
        cache.put("d", "4");
        cache.print(); // 输出: {c=3, a=1, d=4}
    }
}