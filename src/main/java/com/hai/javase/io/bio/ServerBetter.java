package com.hai.javase.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 针对 ServerNormal的改进 伪异步 IO
 * 实现很简单，我们只需要将新建线程的地方，交给线程池管理即可
 * Created by Administrator on 2018/4/12.
 */
public class ServerBetter {

    //端口
    private static int DEFAULT_PORT = 12345;

    //单例的 ServerSocket
    private static ServerSocket server;
    private static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public synchronized static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int port) throws IOException {
        if (null != server) {
            return;
        }
        try {
            server = new ServerSocket(port);
            System.out.println("服务器已启动，端口号：" + port);
            //通过无线循环监听客户端连接
            //如果没有客户端接入，将阻塞在accept操作上。
            while (true) {
                Socket socket = server.accept();
                //当有新的客户端接入时，会执行下面的代码
                //然后创建一个新的线程处理这条Socket链路
                executorService.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //一些必要的清理工作
            if (null != server) {
                System.out.println("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }
}
