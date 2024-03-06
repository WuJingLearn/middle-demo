package org.javaboy.visitor.demo1;

public class WordFile implements File {
    @Override
    public void handleCompress(FileVisitor visitor) {
        visitor.forWorld(this);
    }

    @Override
    public void handlePrint(FileVisitor visitor) {
        visitor.forWorld(this);
    }
}
