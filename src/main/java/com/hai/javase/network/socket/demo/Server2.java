package com.hai.javase.network.socket.demo;

import com.hai.javase.network.socket.CommonSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 支持多客户端模式，用线程可进行异步并行处理。乱码处理。
 * Created by Administrator on 2017/9/10.
 */
public class Server2 {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(CommonSocket.SERVER_PORT);
        while (true) {
            new Thread(new Task(server.accept())).start();
        }
    }

    static class Task implements Runnable {
        Socket socket;

        public Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                String content = CommonSocket.readContent(reader);
                System.out.println("client info:\n" + content);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                writer.write("hello client!i'm server2...");
                writer.write("中国");
                writer.write(CommonSocket.END_OF_STRING);
                writer.flush();

                CommonSocket.close(socket, reader, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
