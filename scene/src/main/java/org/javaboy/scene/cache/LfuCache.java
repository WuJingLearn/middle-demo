package org.javaboy.scene.cache;

import java.util.*;

/**
 * @author:majin.wj
 */
public class LfuCache {


    static class StatisticsObj implements Comparable<StatisticsObj> {
        private String key;
        private Integer count;

        public void setKey(String key) {
            this.key = key;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getKey() {
            return key;
        }

        public Integer getCount() {
            return count;
        }

        @Override
        public boolean equals(Object obj) {
            StatisticsObj other = (StatisticsObj) obj;
            if (other.key.equals(key)) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(StatisticsObj o) {
            return o.key.compareTo(this.key);
        }

        @Override
        public String toString() {
            return "StatisticsObj{" +
                    "key='" + key + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

    static class Lfu {

        private Map<String, String> map = new HashMap<>();
        private TreeMap<StatisticsObj, Integer> treeMap = new TreeMap(new Comparator<StatisticsObj>() {
            @Override
            public int compare(StatisticsObj o1, StatisticsObj o2) {
                return o2.getCount().compareTo(o1.getCount());
            }
        });

        private int size;

        public Lfu(int size) {
            this.size = size;
        }
        public void put(String key, String value) {
            String oldValue = map.put(key, value);
            if (oldValue == null) {
                StatisticsObj statisticsObj = new StatisticsObj();
                statisticsObj.setKey(key);
                statisticsObj.setCount(1);
                treeMap.put(statisticsObj, 1);
            } else {
                StatisticsObj statisticsObj = new StatisticsObj();
                statisticsObj.setKey(key);
                Integer i = treeMap.get(statisticsObj);
                statisticsObj.setCount(i + 1);
                treeMap.put(statisticsObj, i + 1);
            }

            if (map.size() > size) {
                StatisticsObj statisticsObj = treeMap.firstKey();
                treeMap.remove(statisticsObj);
                map.remove(statisticsObj.getKey());
            }
        }

        public Map get(){
            return map;
        }
    }

    public static void main(String[] args) {
//
//
//        TreeSet<StatisticsObj> set = new TreeSet<>();
//
//        StatisticsObj statisticsObj = new StatisticsObj();
//        statisticsObj.setCount(1);
//        statisticsObj.setKey("hh");
//        set.add(statisticsObj);
//
//
//        StatisticsObj statisticsObj2 = new StatisticsObj();
//        statisticsObj2.setKey("hh");
//        statisticsObj2.setCount(2);
//
//        boolean contains = set.contains(statisticsObj2);
//        System.out.println(contains);
//        // equals相同,就会被移除掉
//        boolean remove = set.remove(statisticsObj2);
//
//        System.out.println(remove);


        Lfu lfu = new Lfu(3);

        lfu.put("name","zs");
        lfu.put("age","zs");
        lfu.put("sex","mail");
        lfu.put("sex","mail");

        lfu.put("a","b");

        System.out.println(lfu.get());


    }

}
