package org.javaboy.nio.client;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {


    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        Selector selector = Selector.open();
        // connect是异步的，
        if (socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999))) {
            System.out.println("已经连接成功");
            // 已经连接成功，注册read事件
            socketChannel.register(selector, SelectionKey.OP_READ);

        } else {

            // 组册连接事件，当连接完成时，通知
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }


        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                SocketChannel sc = (SocketChannel) key.channel();
                // 连接事件，表示连接建立完成
                if(key.isConnectable()) {
                    System.out.println(sc.isConnected());
                    sc.register(selector, SelectionKey.OP_CONNECT);
                }else if(key.isReadable()) {

                }
                iterator.remove();
            }
        }



    }

}
