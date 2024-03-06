package org.javaboy.visitor.demo1;

/**
 * 具体的访问者，
 */
public class PrintVisitor implements FileVisitor {
    @Override
    public void forText(File file) {
        System.out.println("打印Text文件");
    }

    @Override
    public void forWorld(File file) {
        System.out.println("打印world文件");
    }
}
