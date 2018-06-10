package com.hai.javase.io.nio.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by Administrator on 2018/4/26.
 */
public class Server {
    //    public static final String SERVER_HOST_ADDRESS = "localhost";
    public static final String SERVER_HOST_ADDRESS = "127.0.0.1";
    public static final int SERVER_PORT = 8888;
    public static final int SELECT_TIMEOUT = 1000;

    private int port;
    private String host;

    public Server() {
        this(SERVER_PORT);
    }

    public Server(int port) {
        this(port, SERVER_HOST_ADDRESS);
    }

    public Server(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() {
        System.out.println(this.getClass().getName() + " pre start...");
        init();
        System.out.println(this.getClass().getName() + " after start...");
    }

    public void init() {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.socket().bind(new InetSocketAddress(host, port));
            server.register(selector, SelectionKey.OP_ACCEPT);
            process(server, selector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process(ServerSocketChannel server, Selector selector) throws IOException {
        while (true) {
            selector.select(SELECT_TIMEOUT);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (selectionKeys.isEmpty()) {
                continue;
            }
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                if (!selectionKey.isValid()) {
                    continue;
                }

                if (selectionKey.isAcceptable()) {
                    accepted(selectionKey);
                }

                if (selectionKey.isReadable()) {
                    read(selectionKey);
                }

                if (selectionKey.isWritable()) {
                    write(selectionKey);
                }
            }
        }
    }

    private void accepted(SelectionKey selectionKey) {
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
            try {
                SocketChannel socketChannel = channel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        try {
            client.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            if (client.read(byteBuffer) > 0) {
                byteBuffer.flip();
                byte[] byteArray = new byte[byteBuffer.limit()];
                byteBuffer.get(byteArray);
                String receiveMessage = new String(byteArray, 0, byteArray.length, "UTF-8");
                System.out.println("receive message from client: " + receiveMessage);
                client.register(key.selector(), SelectionKey.OP_WRITE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                client.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            key.cancel();
        }
    }

    private void write(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

        writeBuffer.clear();
        writeBuffer.put("hello, client".getBytes());
        //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
        writeBuffer.flip();
        try {
            while (writeBuffer.hasRemaining()) {
                client.write(writeBuffer);
            }
            client.register(key.selector(), SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                client.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            key.cancel();
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}
