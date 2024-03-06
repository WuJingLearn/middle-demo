package org.javaboy.decorator.base;

public class FileCompressDecorator extends FileUploaderDecorator {

    public FileCompressDecorator(FileUploader wrapper) {
        super(wrapper);
    }

    @Override
    public void upload(String data) {
        super.upload(compress(data));
    }

    String compress(String data) {
        System.out.println("对数据{ " + data + " }" + "进行压缩");
        return "{" + data + "}";
    }
}
