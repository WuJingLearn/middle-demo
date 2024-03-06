package org.javaboy.visitor.demo1;

public interface File {

    void handleCompress(FileVisitor visitor);

    void handlePrint(FileVisitor visitor);

}
