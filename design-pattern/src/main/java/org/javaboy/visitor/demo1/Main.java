package org.javaboy.visitor.demo1;

/**
 *
 * 访问者模式:
 * 允许一个或者多个操作应用到一组对象上，解耦操作和对象本身
 *
 * 访问者的本质是方法的实现逻辑，通过具体的访问者来实现
 *
 */
public class Main {


    public static void main(String[] args) {


        TextFile textFile = new TextFile();

        textFile.handlePrint(new PrintVisitor());

        textFile.handleCompress(new CompressVisitor());

    }
}
