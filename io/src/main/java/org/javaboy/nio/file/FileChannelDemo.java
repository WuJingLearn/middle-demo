package org.javaboy.nio.file;

import java.nio.channels.FileChannel;

/**
 *
 *
 * FileChannel的transferTo就是sendFile实现。
 *
 *
 * kafka中的零拷贝：
 * 1.写broker使用来 mmap
 * 2.消费消息，从文件到网络，使用了sendfile
 */
public class FileChannelDemo {

    public static void main(String[] args) {

    }
}
