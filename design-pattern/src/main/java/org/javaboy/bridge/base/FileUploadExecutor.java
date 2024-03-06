package org.javaboy.bridge.base;

/**
 * 具体的行为抽象
 */
public interface FileUploadExecutor {
    Object upload(String path);
    boolean check(Object obj);
}
