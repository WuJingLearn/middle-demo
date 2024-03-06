package org.javaboy.nacos.ext.event;

/**
 * @author majin.wj
 * @date 2023/6/18 4:51 PM
 * @desc
 */
public class Test {


    class InnnerClass{

    }

    public static void main(String[] args) {
        String canonicalName = Test.class.getCanonicalName();

        System.out.println(canonicalName);


       //org.javaboy.nacos.ext.event.Test.InnnerClass

        String canonicalName1 = InnnerClass.class.getCanonicalName();
        System.out.println(canonicalName1);


        Class<InnnerClass> innerClass = InnnerClass.class;
        boolean memberClass = innerClass.isMemberClass();
        System.out.println(memberClass);

        Class<?> enclosingClass = innerClass.getEnclosingClass();
        System.out.println(enclosingClass);
    }
}
