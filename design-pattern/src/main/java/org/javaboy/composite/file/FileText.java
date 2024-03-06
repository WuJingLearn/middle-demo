package org.javaboy.composite.file;

import java.io.File;

public class FileText implements FileNode {

    private final String filePath;
    private final File file;

    public FileText(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    @Override
    public int getFileCount() {
        if (file.exists()) {
            return 1;
        }
        return 0;
    }

    @Override
    public long getFileSize() {
        if(file.exists()) {
            return file.length();
        }
        return 0;
    }

    @Override
    public void addChildNode(FileNode child) {
    }

    @Override
    public String filePath() {
        return this.filePath;
    }
}
