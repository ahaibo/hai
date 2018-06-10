package com.hai.javase.io.bio;

import com.hai.javase.io.utils.JavaScriptUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2018/4/12.
 */
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter out = null;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String expression;
            Object result;
            while ((expression = reader.readLine()) != null) {
                System.out.println("服务器收到消息：" + expression);
                try {
                    result = JavaScriptUtil.cal(expression);
                } catch (Exception e) {
                    e.printStackTrace();
                    result = "计算错误：" + e.getMessage();
                }
                out.println(result);
            }
        } catch (IOException e) {
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
