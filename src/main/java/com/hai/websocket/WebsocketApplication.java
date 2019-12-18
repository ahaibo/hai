package com.hai.websocket;

import com.hai.websocket.client.MyWebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class WebsocketApplication {

    public static void main(String[] args) throws URISyntaxException {
        MyWebSocketClient client = new MyWebSocketClient(new URI(""));
        // 往websocket服务端发送数据
        client.send("此为要发送的数据内容");
    }
}
