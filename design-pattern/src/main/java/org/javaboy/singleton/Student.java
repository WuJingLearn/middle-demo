package org.javaboy.singleton;

import com.alibaba.fastjson.JSON;

import java.io.*;

/**
 * 单列模式：
 * 单列模式 可以通过
 * 反射，克隆模式，反序列化等方式来破坏；
 *
 * 发射可以获取私有的构造方法，然后执行。
 *
 *
 */
public class Student implements Cloneable, Serializable {

    private String name = "zs";

    public static Student student = new Student();

    private Student() {
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "student{ name = " + this.name + " }";
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Student student1 = Student.student;

        // 克隆可以打破单列模式
        Object clone = student1.clone();

        System.out.println(student1);
        System.out.println(clone);

        // 反序列化也打破了单列模式
        ByteArrayOutputStream out = new ByteArrayOutputStream(1023);
        ObjectOutputStream writer = new ObjectOutputStream(out);
        writer.writeObject(student1);


        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        ObjectInputStream reader = new ObjectInputStream(in);
        // jdk的反序列，也是通过反射创建的对象，拿到构造器，然后创建对象。
        Student student2 = (Student) reader.readObject();

        System.out.println(student2 == student1);

        String jsonString = JSON.toJSONString(student1);

        System.out.println(jsonString);
        Student student3 = JSON.parseObject(jsonString, Student.class);
        System.out.println(student3 == student1);


    }
}
