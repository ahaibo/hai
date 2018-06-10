package com.hai.javase.io.nio.netty.broadcast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelMatcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/5/13.
 */
public class BroadCastChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Gson GSON = new GsonBuilder().create();
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final AtomicInteger RESPONSE = new AtomicInteger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if (ChannelGroups.size() > 0) {
            Message msg = new Message(channel.remoteAddress().toString().substring(1), SDF.format(new Date()));
            ChannelGroups.broadcast(GSON.toJson(msg), new ChannelMatchers());
        }
        ChannelGroups.add(channel);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();
        if (ChannelGroups.contains(ch) && String.valueOf(msg).equals("welcome")) {
            System.out.println(
                    String.format(
                            "receive [%s] from [%s] at [%s]",
                            String.valueOf(msg),
                            ch.remoteAddress().toString().substring(1),
                            SDF.format(new Date())));
            RESPONSE.incrementAndGet();
        }
        synchronized (RESPONSE) {
            System.out.println(RESPONSE.get() + "\t" + ChannelGroups.size());
            if (RESPONSE.get() == ChannelGroups.size() - 1) {
                System.out.println("server close all connected channel");
                ChannelGroups.disconnect();
                RESPONSE.set(0);
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ChannelGroups.discard(ctx.channel());
        RESPONSE.decrementAndGet();
    }

    public static class ChannelMatchers implements ChannelMatcher {

        @Override
        public boolean matches(Channel channel) {
            return true;
        }

    }
}
