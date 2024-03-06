package org.javaboy.composite.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDirectory implements FileNode {

    private final File file;
    private final String filePath;
    public FileDirectory(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * 文件目录下 可以有文件，也可以有目录
     */
    private List<FileNode> childFileNodes = new ArrayList<>();

    @Override
    public int getFileCount() {
        // 目录本身算1个文件数量
        int fileCount = 1;
        for (FileNode node : childFileNodes) {
            fileCount += node.getFileCount();

        }
        return fileCount;
    }

    @Override
    public long getFileSize() {
        int size = 0;
        for (FileNode node : childFileNodes) {
            size += node.getFileSize();
        }
        return size;
    }

    @Override
    public void addChildNode(FileNode child) {
        this.childFileNodes.add(child);
    }

    @Override
    public String filePath() {
        return this.filePath;
    }
}
