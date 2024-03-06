package org.javaboy.builder.base;


/**
 * 建造者模式：
 * 1.流式构造对象，更加优雅，比如在构建发放参数
 */
public class Main {

    public static void main(String[] args) {
        // 在调用接口时，通过建造者模式更加优雅
        RequestParam param = RequestParam.builder().name("zs")
                .age(10)
                .score(10)
                .build();

        System.out.println(param);

    }
}
