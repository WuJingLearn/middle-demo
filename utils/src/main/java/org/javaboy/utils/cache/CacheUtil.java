package org.javaboy.utils.cache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:majin.wj
 */
public class CacheUtil {

    static final CacheObject<Serializable> NULL_OBJECT = new CacheObject<>();


    private Map<String,CacheObject<Serializable>> cacheObjectMap = new ConcurrentHashMap<>();


    public <T extends Serializable> CacheObject<T> getCacheObject(String key) {
        return (CacheObject<T>) cacheObjectMap.get(key);
    }


    public <T extends Serializable> void putCacheObject(String key, CacheObject<T> cacheObject) {
        cacheObjectMap.put(key, (CacheObject<Serializable>) cacheObject);
    }

    public static void main(String[] args) {
        CacheUtil cacheUtil = new CacheUtil();
        CacheObject<String> stringCacheObject = new CacheObject<>();
        stringCacheObject.setData("majin.wj");
        cacheUtil.putCacheObject("name",stringCacheObject);

        Serializable name = cacheUtil.getCacheObject("name").getData();
        System.out.println(name);

        cacheUtil.putCacheObject("sex",NULL_OBJECT);

        CacheObject<Serializable> sex = cacheUtil.getCacheObject("sex");
        if(sex == NULL_OBJECT) {
            System.out.println("数据不存在");
        }
    }
}
