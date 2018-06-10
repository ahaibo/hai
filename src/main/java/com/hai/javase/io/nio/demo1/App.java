package com.hai.javase.io.nio.demo1;

/**
 * Created by Administrator on 2018/4/26.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        new Server().start();
        Thread.sleep(500);
        new Client().start();
    }
}
