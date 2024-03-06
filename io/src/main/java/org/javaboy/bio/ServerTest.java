package org.javaboy.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * bio: 阻塞io，
 * 监听连接，读写数据，都是阻塞的。需要为每个连接都创建线程才能功能
 *
 * 网卡 --》 内核内存 ---〉 堆内存
 *
 * 磁盘 --》 内核内存 --〉 堆内存
 */
public class ServerTest {

    private static ThreadPoolExecutor executor = ((ThreadPoolExecutor) Executors.newCachedThreadPool());

    public static void openServer(int port) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            // 绑定之后，客户端就可以建立连接了
            serverSocket.bind(new InetSocketAddress(8080));
            // 阻塞
            while (true) {
                /**
                 * 在bio中，即使这里走不到，但是客户端依然可以建立连接。但是在建立连接后无法及时处理他
                 */
                handleSocket(serverSocket.accept());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void handleSocket(Socket socket) {
        executor.execute(() -> {
            try {
                InputStream in = socket.getInputStream();
                byte[] data = new byte[in.available()];
                in.read(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
