package org.javaboy.mybatis.source.reflect;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;

/**
 *
 * 1.在处理getter，setting方法时，一个名称对应一个list的原因，是因为可能有继承关系，这样会得到多个getter方法，
 * 最后取哪个getter方法，通过比较子类和父类的返回值。通常最后会选择子类的，因为子类的返回值可能会更小
 *
 * 2.一个Reflector是对一个class中反射的封装。通过ReflectorFactory，可以对这个Reflector进行缓存。
 * 每次通过工厂来获取class的Reflector。这样在不通的地方使用一个class的Reflector就不用多次创建了
 *
 */
public class ReflectionUtilTest
{
    public static void main(String[] args) {

        Reflector reflector = new Reflector(ReflectionUtilTest.class);

        DefaultReflectorFactory factory = new DefaultReflectorFactory();
        Reflector forClass = factory.findForClass(ReflectionUtilTest.class);


    }
}
