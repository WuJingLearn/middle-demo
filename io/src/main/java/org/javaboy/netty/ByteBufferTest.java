package org.javaboy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    public static void main(String[] args) {
        // jdk
        ByteBuffer.allocateDirect(10);

        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.directBuffer(10);
    }
}
