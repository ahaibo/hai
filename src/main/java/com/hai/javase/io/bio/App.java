package com.hai.javase.io.bio;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Administrator on 2018/4/12.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        startServer(true);

        //避免客户端先于服务器启动前执行代码
        Thread.sleep(500);

        startClient();
    }

    private static void startServer() {
        startServer(false);
    }

    private static void startServer(boolean startBetterServer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (startBetterServer) {
                        ServerBetter.start();
                    } else {
                        ServerNormal.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void startClient() {
        char operators[] = {'+', '-', '*', '/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(new Runnable() {
            @SuppressWarnings("static-access")
            @Override
            public void run() {
                while (true) {
                    //随机产生算术表达式
                    String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
                    Client.send(expression);
                    try {
                        Thread.currentThread().sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
