package org.javaboy.volatiletest;

/**
 * @author majin.wj
 * @date 2023/6/26 11:12 AM
 * @desc
 */
public class Cache {

    static Cache cache;

    public static Cache getCache(){

        if(cache == null) {
            cache = new Cache();
        }
        return cache;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Cache cache = Cache.getCache();
                System.out.println(cache);
            }).start();
        }
    }
}
