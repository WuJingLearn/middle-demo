package org.javaboy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Nio:非阻塞io ,accept方法，read 方法都是非阻塞的，通过单线程即可完成所有业务逻辑处理。暂时先不考虑什么消息。
 *
 * 缺点也很明显：每次都是遍历所有的连接，看这些连接上是否有请求。进行了很多次多余的遍历
 *
 * 这个模型是非阻塞io模型
 */
public class ServerStep1 {


    static void openServer(int port) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);

            final List<SocketChannel> sockets = new ArrayList<>();

            while (true) {
                //这里是非阻塞的
                SocketChannel socket = serverSocketChannel.accept();

                if (socket == null) {
                    System.out.println("没有连接建立");
                } else {
                    socket.configureBlocking(false);
                    sockets.add(socket);
                }
                handSockets(sockets);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void handSockets(List<SocketChannel> sockets){

        for (SocketChannel socket : sockets) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = 0;
            try {
                /**
                 * 当没有数据的时候，这里返回-1，而不是阻塞。
                 * 如果有数据，会进行数据的拷贝，拷贝过程是阻塞的
                 *
                 */
                read = socket.read(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(read>0) {

            }
        }

    }


    public static void main(String[] args) {


    }

}
