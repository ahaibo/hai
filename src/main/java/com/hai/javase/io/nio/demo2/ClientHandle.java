package com.hai.javase.io.nio.demo2;

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
 * Created by Administrator on 2018/4/12.
 */
public class ClientHandle implements Runnable {
    private int port;
    private String host;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean started;

    public ClientHandle(String ip, int port) {
        this.host = ip;
        this.port = port;
    }

    private void init() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(host, port));
            synchronized (selector) {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
        process();
    }

    private void process() {
        while (started) {
            try {
                int selectKey = selector.select(ServerHandler.SELECT_TIMEOUT);
                if (!(selectKey > 0)) {
                    continue;
                }
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isConnectable()) {
                        finishConnect(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        write(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void write(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        // byteBuffer.put(ss.getBytes());
        for (int i = 0; i < 10; i++) {
            String ss = i + "Server ,how are you? this is package message from NioSocketClient!";
            ByteBuffer byteBuffer = ByteBuffer.wrap(ss.getBytes());

            System.out.println("[client] send:{" + i + "}-- " + ss);
            while (byteBuffer.hasRemaining()) {
                try {
                    channel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // key.interestOps(SelectionKey.OP_READ);
        try {
            synchronized (selector) {
                channel.register(selector, SelectionKey.OP_READ);
            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    private void finishConnect(SelectionKey key) {
        System.out.println("client finish connect!");
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {
            socketChannel.finishConnect();
            synchronized (selector) {
                socketChannel.register(selector, SelectionKey.OP_WRITE);
                key.interestOps(SelectionKey.OP_WRITE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int len = channel.read(byteBuffer);
        if (len > 0) {
            byteBuffer.flip();
            byte[] byteArray = new byte[byteBuffer.limit()];
            byteBuffer.get(byteArray);
            System.out.println("client[" + host + ":" + port + "]" + " receive from server:");
            System.out.println(new String(byteArray));
            len = channel.read(byteBuffer);
            byteBuffer.clear();

        }
        key.interestOps(SelectionKey.OP_READ);
    }

    //异步发送消息
    private void doWrite(SocketChannel channel, String request) throws IOException {
        //将消息编码为字节数组
        byte[] bytes = request.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
        //****此处不含处理“写半包”的代码
    }

    public void sendMsg(String msg) throws Exception {
        socketChannel.register(selector, SelectionKey.OP_READ);
        doWrite(socketChannel, msg);
    }

    public void stop() {
        this.started = false;
    }
}
