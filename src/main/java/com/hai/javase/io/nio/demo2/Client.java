package com.hai.javase.io.nio.demo2;

public class Client {
    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 12345;
    private static ClientHandle clientHandle;

    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port) {
        if (clientHandle != null) {
            clientHandle.stop();
        }
        clientHandle = new ClientHandle(ip, port);
        new Thread(clientHandle, "Client").start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            sendMsg(Client.class.getName() + System.nanoTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //向服务器发送消息  
    public static boolean sendMsg(String msg) throws Exception {
        if (msg.equals("q")) {
            return false;
        }
        clientHandle.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) {
        start();
    }
}  