package com.hai.ijavase.inetwork.socket.demo;

import com.hai.ijavase.inetwork.socket.CommonSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单客户端连接
 * Created by Administrator on 2017/9/10.
 */
public class Server1 {


    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(CommonSocket.SERVER_PORT);
        Socket socket = serverSocket.accept();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = CommonSocket.readContent(reader);
        System.out.println("client info:\n" + str);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("hello client!...");
        writer.write(CommonSocket.END_OF_STRING);
        writer.flush();

        CommonSocket.close(socket, reader, writer);
    }


}
