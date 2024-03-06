package org.javaboy.composite.file;

public interface FileNode {

    int getFileCount();


    long getFileSize();


    void addChildNode(FileNode child);

    String filePath();

}
