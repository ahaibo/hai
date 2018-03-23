package com.hai.javase.network.socket.demo;

import com.hai.javase.network.socket.CommonSocket;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/9/10.
 */
public class Client {

    public static void main(String[] args) {
        Client client = new Client();
//        client.test3();
        client.test4();
    }

    @Test
    public void test1() {
        work();
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            work();
        }
    }

    @Test
    public void test3() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
//            new Thread(new Task()).main();
            executorService.submit(new Task());
        }
        executorService.shutdown();
        if (executorService.isTerminated()) {
            System.out.println("job done!");
        }
    }

    public void test4() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task()).start();
        }
    }

    class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("ThreadName: " + Thread.currentThread().getName());
            work();
            System.out.println();
        }
    }

    public void work() {
        Socket socket = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            socket = new Socket("localhost", CommonSocket.SERVER_PORT);
//        InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), Server1.SERVER_PORT);
//        socket.connect(socketAddress);
            socket.setSoTimeout(CommonSocket.SOCKET_TIMEOUT);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("hello server!...");
            writer.write(CommonSocket.END_OF_STRING);
            writer.flush();

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = CommonSocket.readContent(reader);
            System.out.println("server info:\n" + str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommonSocket.close(socket, reader, writer);
        }
    }
}
