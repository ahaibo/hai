package com.hai.javase.io.aio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2018/6/2.
 */
public class SimpleAIOServer {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
            server.bind(new InetSocketAddress(PORT));
            while (true) {
                //接收客户端连接
                Future<AsynchronousSocketChannel> future = server.accept();
                //获取连接完成后端 AsynchronousSocketChannel
                AsynchronousSocketChannel channel = future.get(100, TimeUnit.SECONDS);

                buffer.clear();
//                channel.read(buffer).get();
                channel.read(buffer, null, new CompletionHandler<Integer, Object>() {

                    @Override
                    public void completed(Integer result, Object attachment) {
                        buffer.flip();
                        String content = StandardCharsets.UTF_8.decode(buffer).toString();
                        System.out.println("服务端收到客户端消息：" + content);

                        //读取下一次数据
                        buffer.clear();
                        channel.read(buffer, null, this);
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("read failed: " + exc.getMessage());
                    }
                });
//                String content = StandardCharsets.UTF_8.decode(buffer).toString();
//                System.out.println("服务端收到客户端消息：" + content);

                //输出
                channel.write(ByteBuffer.wrap(("欢迎来到AIO的世界" + channel.getRemoteAddress()).getBytes())).get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
