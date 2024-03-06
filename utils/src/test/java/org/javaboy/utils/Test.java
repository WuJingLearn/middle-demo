package org.javaboy.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:majin.wj
 */
public class Test extends HashMap {

    public String name;


    public static void main(String[] args) {
//        Field[] declaredFields = Test.class.getDeclaredFields();
        Field[] declaredFields = Test.class.getFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        Field[] allField = getAllField(Test.class);
        System.out.println(allField.length);

    }


    public static Field[] getAllField(Class<?> cls) {
        Class<?> curClass = cls;
        List<Field> allFields = new ArrayList<>();
        while (curClass != null && curClass != Object.class) {
            Field[] declaredFields = curClass.getDeclaredFields();
            allFields.addAll(Arrays.stream(declaredFields).collect(Collectors.toList()));
            curClass = curClass.getSuperclass();
        }
        return allFields.toArray(new Field[0]);
    }

}
