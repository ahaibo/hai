package com.hai.javase.io.nio.netty.broadcast;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


/**
 * Created by Administrator on 2018/5/13.
 */
public class BroadCastServer {

    public static final int BUFFER_SIZE = 1024;

    private int port;

    public BroadCastServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, BUFFER_SIZE)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                                    .addLast("frameEncoder", new LengthFieldPrepender(4))
                                    .addLast("decoder", new StringDecoder(CharsetUtil.UTF_8))
                                    .addLast("encoder", new StringEncoder(CharsetUtil.UTF_8))
                                    .addLast(new BroadCastChannelHandler());
                        }
                    });

            ChannelFuture future = bootstrap.bind(port).sync();
            if (future.isDone()) {
                System.out.println(String.format("server bind port %s sucess", port));
            }

            //CloseFuture异步方式关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭所有的event loop来终止所有线程
            boss.shutdownGracefully();
            worker.shutdownGracefully();

            // 等待所有线程终止
            try {
                boss.terminationFuture().sync();
                worker.terminationFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BroadCastServer(8888).run();
        System.out.println("stared...");
    }
}
