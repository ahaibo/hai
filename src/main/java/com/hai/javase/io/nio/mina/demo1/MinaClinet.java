package com.hai.javase.io.nio.mina.demo1;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2018/6/1.
 */
public class MinaClinet {
    public static void main(String[] args) {
        // 第一步，建立一个connecter
        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);

        // 第二步，设置消息处理的Handler
        connector.setHandler(new MinaClinetHandler());

        // 第三步骤，设置过滤器
        connector.getFilterChain().addLast("minaClinet",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"))));
//        connector.getFilterChain().addLast("exec", new ExecutorFilter());

        // 第四步骤，连接服务器
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress("localhost", 9999));

        // 阻塞等待连接创建
//       connectFuture.awaitUninterruptibly();

        //异步方式连接
        connectFuture.addListener(new IoFutureListener<IoFuture>() {
            @Override
            public void operationComplete(IoFuture future) {
                System.out.println(this.getClass().getName() + ".operation complete...");
                IoSession session = future.getSession();
                session.write("client connect operation complete...");
            }
        });

//        sendMessage(connector, connectFuture.getSession());

        //发送接收消息
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
            String msg = null;
            boolean toContinue = true;
            while (toContinue) {
                toContinue = !(msg = reader.readLine()).equals("exit");
                if (null != connectFuture.getSession()) {
                    connectFuture.getSession().write("client send--" + msg);
                }
            }
            connector.dispose();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(MinaClinet.class.getName() + ".main called...");
    }

}
