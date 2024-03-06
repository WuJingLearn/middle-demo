package org.javaboy.decorator.base;

public class FileUploaderDecorator implements FileUploader {

    private FileUploader wrapper;

    public FileUploaderDecorator(FileUploader wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void upload(String data) {
        wrapper.upload(data);
    }
}
