package org.javaboy.visitor.demo1;

public class CompressVisitor implements FileVisitor{


    @Override
    public void forText(File file) {
        System.out.println("压缩文本文件");
    }

    @Override
    public void forWorld(File file) {
        System.out.println("压缩world文件");
    }
}
