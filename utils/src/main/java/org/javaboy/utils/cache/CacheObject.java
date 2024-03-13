package org.javaboy.utils.cache;

import java.io.Serializable;

/**
 * @author:majin.wj
 */
public class CacheObject<T extends Serializable> {

    // 实际缓存的数据
    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
