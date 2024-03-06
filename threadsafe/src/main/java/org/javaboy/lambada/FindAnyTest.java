package org.javaboy.lambada;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;

/**
 * findAny是终止运算
 */
public class FindAnyTest {
    public static void main(String[] args) {
        Optional<String> result = Arrays.stream(new String[]{"zs", "lss"}).filter(str -> {
            System.out.println("执行");
            return str.length() > 1;
        }).findAny();
        result.ifPresent(System.out::println);



    }

    private Constructor defaultConstructor;
     void t1(){
        Class<FindAnyTest> clazz = FindAnyTest.class;
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Arrays.stream(constructors).filter(constructor -> constructor.getParameterTypes().length == 0).findAny()
                .ifPresent(constructor -> this.defaultConstructor = constructor);
    }
}
