package org.javaboy.bridge.base;

/**
 * 定义抽象实体类
 */
public interface FileUploader {

    Object upload(String path);


    boolean check(Object obj);


}
