package org.javaboy.nio;


/**
 * 可以看到在Server2中，虽然使用了Io多路复用模型，但是依然存在一些问题。
 * 当在处理连接的数据时，此时如果有新的连接建立请求时，这时还在处理数据，是无法处理连接建立请求的。
 * 需要等待所有连接上的读请求处理完成之后，才能继续处理连接建立事件。
 * server2就是单reactor 单线程模型。
 *
 *
 * Redis6之前的，就是典型的单Reactor模型，一个线程负责接受连接，从连接上读写数据，处理业务逻辑。
 *
 *
 * 这时就要设计到编程模型；
 * 主从Reactor多线程模型
 *
 */
public class ServerStep3 {
}