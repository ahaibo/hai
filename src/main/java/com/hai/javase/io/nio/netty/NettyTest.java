package com.hai.javase.io.nio.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * Created by Administrator on 2018/11/1.
 */
public class NettyTest {

    int bufferSize = 1024;

    @Test
    public void test1() {
        ByteBuf byteBuf = Unpooled.copiedBuffer(new byte[bufferSize]);
        System.out.println(JSONObject.toJSONString(byteBuf));

        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        System.out.println(JSONObject.toJSONString(compositeByteBuf));

    }
}
