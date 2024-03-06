package org.javaboy.threadlocal.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author:majin.wj
 */
public class RpcContextUtil {


    private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<>();

    public RpcContextUtil() {

    }


    public static void setAttribute(String key, Object data) {
        if (context.get() == null) {
            context.set(new HashMap<>());
            context.get().put(key, data);
        } else {
            context.get().put(key, data);
        }
    }

    public static Object getAttribute(String key) {
        Map<String, Object> map = context.get();
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

}
