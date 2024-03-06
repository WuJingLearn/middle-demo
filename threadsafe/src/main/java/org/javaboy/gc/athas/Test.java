package org.javaboy.gc.athas;

import java.io.PrintStream;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author:majin.wj curl -O https://arthas.aliyun.com/arthas-boot.jar 下载arthas
 */
public class Test {


    public static int a = 1;
    private static String name = "zs";

    public static  Person person = new Person();

    public static String setName(String changeName){
        name = changeName;
        System.out.println(name);
        return name;
    }

    public static String setPerson(Person person){
        System.out.println(person);
        return "success";
    }

    public static void main(String[] args) {

//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
//                    int b = 1 + 1;
//                    test3();
//                    System.out.println("执行程序");
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        }).start();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void test3() {
        int i = 0;
        while (i < 100) {
            System.out.println(i++);
        }
    }

    public static void test2(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test1() {
        int i = 0;
        while (i < 20) {
            System.out.println(i++);
        }
    }

}
