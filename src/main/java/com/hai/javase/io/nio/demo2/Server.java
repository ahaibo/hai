package com.hai.javase.io.nio.demo2;

/**
 * Created by Administrator on 2018/4/12.
 */
public class Server {

    public static final int DEFAULT_PORT = 12345;
    private static ServerHandler serverHandler;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public static void start(int port) {
        if (null != serverHandler) {
            serverHandler.stop();
        }
        serverHandler = new ServerHandler(port);
        new Thread(serverHandler, "Server").start();
    }

    public static void main(String[] args) {
        start();
    }
}
