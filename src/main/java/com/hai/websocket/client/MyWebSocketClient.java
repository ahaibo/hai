package com.hai.websocket.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class MyWebSocketClient extends WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(MyWebSocketClient.class);

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("---------- MyWebSocketServer onOpen ----------");
    }

    @Override
    public void onMessage(String s) {
        logger.info("---------- 接收到服务端数据： {} ----------", s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.info("---------- 连接关闭 i:{}, s:{}, b:{} ----------", i, s, b);
    }

    @Override
    public void onError(Exception e) {
        logger.info("---------- 客户端连接出现异常:{} ----------", e.getMessage(), e);
    }
}
