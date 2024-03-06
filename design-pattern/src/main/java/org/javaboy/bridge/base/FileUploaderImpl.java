package org.javaboy.bridge.base;

/**
 * 具体的实体类
 */
public class FileUploaderImpl implements FileUploader{

    private FileUploadExecutor executor;

    public FileUploaderImpl(FileUploadExecutor executor) {
        this.executor = executor;
    }
    @Override
    public Object upload(String path) {
        return null;
    }

    @Override
    public boolean check(Object obj) {
        return false;
    }
}
