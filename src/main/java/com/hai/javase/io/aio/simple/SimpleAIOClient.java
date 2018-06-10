package com.hai.javase.io.aio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2018/6/2.
 */
public class SimpleAIOClient {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
            client.connect(new InetSocketAddress("localhost", PORT)).get();

            client.write(ByteBuffer.wrap("hello, server...".getBytes())).get();

            buffer.clear();
            client.read(buffer).get();
            buffer.flip();

            String content = StandardCharsets.UTF_8.decode(buffer).toString();
            System.out.println("服务器信息：" + content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
