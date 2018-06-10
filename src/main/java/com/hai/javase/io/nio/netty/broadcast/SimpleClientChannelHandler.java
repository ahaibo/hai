package com.hai.javase.io.nio.netty.broadcast;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleClientChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(
                String.format("client(%s) receive message [%s]",
                        channel.localAddress().toString().substring(1),
                        String.valueOf(msg)));
        System.out.println();
        ctx.writeAndFlush(String.valueOf("welcome"));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ctx.disconnect(ctx.newPromise());
        ctx.close();
        System.out.println(
                String.format(
                        "client(%s) close sucess",
                        ctx.channel().localAddress().toString().substring(1)));
    }
}