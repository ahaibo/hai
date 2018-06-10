package com.hai.javase.io.nio.mina.demo1;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2018/6/1.
 */
public class MinaServer {

    private static final int PORT = 9999;

    public static void main(String[] args) {
        try {// 第一步，新建一个accepter对象
            NioSocketAcceptor acceptor = new NioSocketAcceptor();

            // 第二步骤，设置Handler
            acceptor.setHandler(new MinaServerHandler());

            // 第三步，设置拦截器，设置成自带的拦截器
            acceptor.getFilterChain().addLast(
                    "minaServer",
                    new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"))));
            //配置session相关属性
            acceptor.getSessionConfig().setReadBufferSize(1024);
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

            // 第四步，创建端口连接
            acceptor.bind(new InetSocketAddress(PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
