package org.javaboy.visitor.demo1;

public class TextFile implements File{
    @Override
    public void handleCompress(FileVisitor visitor) {
        visitor.forText(this);
    }

    @Override
    public void handlePrint(FileVisitor visitor) {
        visitor.forText(this);
    }
}
