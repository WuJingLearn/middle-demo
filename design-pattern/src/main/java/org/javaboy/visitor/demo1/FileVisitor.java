package org.javaboy.visitor.demo1;

/**
 * 访问者接口
 */
public interface FileVisitor {

    /**
     * 访问文本文件
     * @param file
     */
    void forText(File file);


    /**
     * 访问world文件
     * @param file
     */
    void forWorld(File file);

}
