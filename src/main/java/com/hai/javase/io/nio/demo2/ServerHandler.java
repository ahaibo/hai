package com.hai.javase.io.nio.demo2;

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
 * 创建NIO服务端的主要步骤如下：
 * <p>
 * 01.打开ServerSocketChannel，监听客户端连接
 * 02.绑定监听端口，设置连接为非阻塞模式
 * 03.创建Reactor线程，创建多路复用器并启动线程
 * 04.将ServerSocketChannel注册到Reactor线程中的Selector上，监听ACCEPT事件
 * 05.Selector轮询准备就绪的key
 * 06.Selector监听到新的客户端接入，处理新的接入请求，完成TCP三次握手，简历物理链路
 * 07.设置客户端链路为非阻塞模式
 * 08.将新接入的客户端连接注册到Reactor线程的Selector上，监听读操作，读取客户端发送的网络消息
 * 09.异步读取客户端消息到缓冲区
 * 10.对Buffer编解码，处理半包消息，将解码成功的消息封装成Task
 * 11.将应答消息编码为Buffer，调用SocketChannel的write将消息异步发送给客户端
 * Created by Administrator on 2018/4/12.
 */
public class ServerHandler implements Runnable {

    public static final int BACK_LOG_SIZE = 1024;
    public static final long SELECT_TIMEOUT = 1000;
    private int port;
    private Selector selector;
    private ServerSocketChannel channel;
    private SelectionKey selectionKey;
    private volatile boolean started;


    public ServerHandler(int port) {
        this.port = port;
    }

    private void init() {
        try {
            //创建复用器
            selector = Selector.open();

            //打开监听通道
            channel = ServerSocketChannel.open();

            //设置通道为非阻塞模式
            channel.configureBlocking(false);

            //绑定地址
            channel.socket().bind(new InetSocketAddress(port), BACK_LOG_SIZE);

            //监听客户端请求
            selectionKey = channel.register(selector, SelectionKey.OP_ACCEPT);

            //标记服务器已开启
            started = true;
            System.out.println("服务器已启动，端口号：" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        synchronized (this) {
            started = false;
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
                //无论是否有读写事件发生，selector每隔1s被唤醒一次
                int selectKey = selector.select(SELECT_TIMEOUT);
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
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        write(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (null != channel) {
                    try {
                        channel.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        //selector关闭后会自动释放里面管理的资源
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("is acceptable");
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) {
        System.out.println("read operation");
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        int len = 0;
        try {
            len = socketChannel.read(byteBuffer);
            if (len > 0) {
                byteBuffer.flip();
                byte[] byteArray = new byte[byteBuffer.limit()];
                byteBuffer.get(byteArray);
                System.out.println("receive from client info: " + new String(byteArray));
            }
            key.interestOps(SelectionKey.OP_READ);
        } catch (IOException e) {
            try {
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            key.cancel();
            e.printStackTrace();
        }

    }

    private void write(SelectionKey key) {
        StringBuilder httpResponse = new StringBuilder("");
        httpResponse
                .append("HTTP/1.1 200 OK\r\n")
                .append("Content-Length: 38\r\n")
                .append("Content-Type: text/html\r\n\r\n")
                .append("<html><body>Hello World!</body></html>");
        write(key, httpResponse.toString());
    }

    private void write(SelectionKey key, String response) {
        System.out.println("write operation");
        SocketChannel socketChannel = (SocketChannel) key.channel();
        System.out.println("response from server to client");
        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(response.getBytes());
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            selectionKey.cancel();
        } catch (IOException e) {
            try {
                selectionKey.cancel();
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
