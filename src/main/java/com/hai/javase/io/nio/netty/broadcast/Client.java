package com.hai.javase.io.nio.netty.broadcast;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private static final String host = "127.0.0.1";
    private static final int port = 8080;
    private static final ExecutorService es = Executors.newFixedThreadPool(5);

    public static void start() {
        for (int i = 0; i < 5; i++) {
            es.execute(new Task());
        }
        es.shutdown();
    }

    public static class Task implements Runnable {

        @Override
        public void run() {
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap
                        .group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {

                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                                pipeline.addLast(new SimpleClientChannelHandler());
                            }

                        });
                ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
                if (channelFuture.isSuccess()) {
                    System.out.println(String.format("connect server(%s:%s) sucess", host, port));
                }
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
        }

    }

    public static void main(String[] args) {
        Client.start();
    }
}
 
 
