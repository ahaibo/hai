package com.hai.test.suggest151;

import org.junit.Test;

/**
 * 建议122: 使用线程异常处理器提升系统可靠性
 * <p>
 * 以下程序只是简单实例，在生成环境还需考虑以下问题：
 * 1.共享资源锁定
 * 2.脏数据引起系统逻辑混乱
 * 3.内存溢出
 * </p>
 * Created by Administrator on 2017/10/1.
 */
public class Sug112_UnexpectedExceptionHandler {

    @Test
    public void test() {
        new TCPServer().start();
    }

    class TCPServer implements Runnable {

        public TCPServer() {
        }

        public void start() {
            System.out.println(getClass().getName() + ".main...");
            Thread t = new Thread(this);
            t.setUncaughtExceptionHandler(new TCPServerUncaughtExceptionHandler(this));
            t.start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 300; i++) {
                try {
                    Thread.sleep(500);
                    System.out.println("thread " + i + " running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //模拟抛出异常
            throw new RuntimeException("模拟抛出异常");
        }

        public void restart() {
            System.out.println(getClass().getName() + ".restart: new TCPServer().main();");
            new TCPServer().start();
        }
    }

    class TCPServerUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        TCPServer tcpServer;

        public TCPServerUncaughtExceptionHandler(TCPServer tcpServer) {
            this.tcpServer = tcpServer;
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + " caught exception..");
            System.out.println("exception stack: ");
            e.printStackTrace();

            //restart
            tcpServer.restart();
        }
    }

}
