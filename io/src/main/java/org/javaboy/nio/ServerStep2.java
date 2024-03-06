package org.javaboy.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio:通过多路复用的方式，一个线程可以处理多个连接。先不用考虑效率的问题。
 * 这个列子没有采用轮训的方法，遍历所有的连接，看这个连接上是否有数据过来，
 * 而是借助多路复用器，每次处理有事件的连接
 * <p>
 * 这个IO模型：是IO多路复用模型；
 * Nio多路复用：是一个非阻塞同步IO；同步说的是，应用程序依然需要主动从内核将数据拷贝到用户工作空间
 * <p>
 * 阻塞/非阻塞io 同步/非同步io 概念:https://blog.csdn.net/qq_35240226/article/details/82874174
 * 阻塞或者非阻塞是指应用程序在发起IO操作时，是立即返回还是等待
 * 同步或异步是指应用程序与内核通信时。数据从内核空间到应用空间的拷贝，是由内核主动发起，还是应用程序来来发出
 * <p>
 *
 *
 * OP_READ事件：当操作系统读缓冲区有数据可读时就绪，并非所有时刻都有数据可读，所以一般需要注册该操作。，
 * 仅仅当就绪时才发起读操作。
 * OP_WRITE:当操作系统写缓冲区有空闲空间的时候就绪，一般情况下写缓冲区都有空闲空间，小块数据直接写入即可，
 * 没必要注册该事件，否则该事件会不断就绪浪费cpu.但是如果写密集任务，比如文件下载，缓冲区可能满，注册写事件就很
 * 有必要，同时注意写完后取消该事件/
 * OP_CONNECT:当socoketChannel.connect请求连接成功之后就绪
 * OP_ACCEPT: 当收到一个客户端连接时就绪。
 *
 * 写事件： 在发送数据的时候，直接发送就可以，注册个写事件
 */
public class ServerStep2 {

    public static void openServer(int port) {

        try {

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            // 多路复用器
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                //阻塞 直到有一个channel有事件就绪
                /**
                 * 事件就绪的意思是：如果有读请求，表示数据已经从网卡拷贝到了内核态。
                 *
                 *
                 */
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.attachment();
                        SocketChannel socketChannel = server.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        // handRead
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        // 这一步是将内核数据拷贝到用户空间，这个过程依然是一个阻塞的
                        int read = socketChannel.read(buffer);
                        if (read > 0) {
                            // 写模式切换成读模式
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            // 读取到字节数组中
                            buffer.get(bytes);
                            System.out.println("收到数据："+new String(bytes));
                            write(socketChannel,selector);
                        }

                    }else if(selectionKey.isWritable()) {
                        //
                        SocketChannel sc = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        if(buffer!=null && buffer.hasRemaining()) {
                            sc.write(buffer);
                        }else {
                            selectionKey.attach(null);
                            // 取消对写的注册，只关注读事件
                            selectionKey.interestOps(SelectionKey.OP_READ);
                        }

                    }
                    /**
                     * 必须删除，将处理完的selectionKey删除，否则这个感兴趣的事件将会一直存在。
                     */
                    iterator.remove();
                }
            }

        } catch (Exception e) {

        }
    }

    static void write(SocketChannel socketChannel,Selector selector) throws ClosedChannelException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello".getBytes());
        // 注册了读写事件
        socketChannel.register(selector,SelectionKey.OP_WRITE|SelectionKey.OP_READ,buffer);
    }
}
