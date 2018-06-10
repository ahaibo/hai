package com.hai.javase.io.nio.mina.demo1;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Administrator on 2018/6/1.
 */
public class MinaServerHandler extends IoHandlerAdapter {
    public MinaServerHandler() {
        System.out.println("constructor...");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        System.out.println(this.getClass().getName() + ".sessionCreated...");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        System.out.println(this.getClass().getName() + ".sessionOpened...");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println(this.getClass().getName() + ".sessionClosed...");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        System.out.println(this.getClass().getName() + ".sessionIdle...");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        System.out.println(this.getClass().getName() + ".exceptionCaught...");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        System.out.println(this.getClass().getName() + ".messageReceived: " + message);
        // 向客户端回复消息
        session.write("server replay--" + message);
        if ("exit".equals(message)) {
//            session.getCloseFuture().await();
            session.closeOnFlush();
//            session.closeNow();
//            session.close();
            System.out.println(this.getClass().getName() + ".closed-" + System.nanoTime());
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        System.out.println(this.getClass().getName() + ".messageSent...");
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
        System.out.println(this.getClass().getName() + ".inputClosed...");
    }
}
