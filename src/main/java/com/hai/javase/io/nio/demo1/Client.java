package com.hai.javase.io.nio.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/26.
 */
public class Client {

    public void start() {
        System.out.println(this.getClass().getName() + " pre start...");
        init();
        System.out.println(this.getClass().getName() + " after start...");
    }

    public void init() {
        try {
            Selector selector = Selector.open();
            SocketChannel client = SocketChannel.open();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_CONNECT);
            client.connect(new InetSocketAddress(Server.SERVER_HOST_ADDRESS, Server.SERVER_PORT));
            process(selector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process(Selector selector) {
        try {
            selector.select(Server.SELECT_TIMEOUT);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (!selectionKeys.isEmpty()) {
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isConnectable()) {
                        connected(key);
                    }
                    if (key.isReadable()) {
                        read(key);
                    }
                    if (key.isWritable()) {
                        write(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connected(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        // 判断此通道上是否正在进行连接操作
        if (client.isConnectionPending()) {
            try {
                // 完成套接字通道的连接过程
                client.finishConnect();
                System.out.println("completed connection from client...");

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put("hello,server,connected...".getBytes());
                buffer.flip();
                while (buffer.hasRemaining()) {
                    client.write(buffer);
                }

                client.register(key.selector(), SelectionKey.OP_READ);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            if (client.read(buffer) > 0) {
                byte[] byteArray = new byte[buffer.limit()];
                buffer.get(byteArray);
                String message = new String(byteArray, 0, byteArray.length, "UTF-8");
                System.out.println("received message from client: " + message);
                client.register(key.selector(), SelectionKey.OP_WRITE);
            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            buffer.put("hello,server,write...".getBytes());
            while (buffer.hasRemaining()) {
                client.write(buffer);
            }
            client.register(key.selector(), SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }
}
