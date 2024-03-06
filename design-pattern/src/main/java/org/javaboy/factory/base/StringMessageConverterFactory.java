package org.javaboy.factory.base;

import org.javaboy.factory.base.support.JSONMessageConverter;
import org.javaboy.factory.base.support.XmlMessageConverter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂方法模式:
 * 如果对象是有状态的，那么每次调用都会返回一个新对象
 * 如果对象是无法状态的，使用一个map进行缓存，相同的类型使用同一个对象即可
 */
public class StringMessageConverterFactory {


    private static Map<String, MessageConverter> messageConverterMap = new ConcurrentHashMap<>();

    public static Object getObject(String type) {
        return new Object();
    }

    public static Object getMessageCovert(String type) {
        return messageConverterMap.computeIfAbsent(type, k -> {
            switch (type) {
                case "json": {
                    return new JSONMessageConverter();
                }
                case "xml": {
                    return new XmlMessageConverter();
                }
                default:
                    System.out.println("Cant find messageConverter type:" + type);
                    return null;
            }
        });
    }
}
