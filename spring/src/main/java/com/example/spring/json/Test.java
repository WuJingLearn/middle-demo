package com.example.spring.json;

import com.alibaba.fastjson.JSON;

/**
 * @author:majin.wj
 */
public class Test {
    public static void main(String[] a) {
        Stu stu = new Stu();
        stu.setName("zs");

        School school = new School();
        school.setName("一中");

        Object[] args = new Object[]{stu,school};

        String jsonString = JSON.toJSONString(args);
        System.out.println(jsonString);
        byte[] bytes = jsonString.getBytes();

        Object[] objects = JSON.parseObject(new String(bytes), Object[].class);
        System.out.println(objects);

        System.out.println(objects[0].getClass());
        System.out.println(objects[1].getClass());

    }
}
