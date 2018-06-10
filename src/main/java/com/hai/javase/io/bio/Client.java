package com.hai.javase.io.bio;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2018/4/12.
 */
public class Client {

    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 12345;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String expression) {
        send(DEFAULT_SERVER_PORT, expression);
    }

    public static void send(int port, String expression) {
        System.out.println("算术表达式为：" + expression);
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter out = null;

        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(expression);
            System.out.println("result: " + reader.readLine() + "\n");

            Thread.sleep(100);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                out.close();
            }
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
