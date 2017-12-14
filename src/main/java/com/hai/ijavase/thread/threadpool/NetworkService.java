package com.hai.ijavase.thread.threadpool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 下面给出了一个网络服务的简单结构，这里线程池中的线程作为传入的请求。它使用了预先配置的 Executors.newFixedThreadPool(int)
 * 工厂方法
 *
 * @author Administrator
 */
public class NetworkService implements Runnable {
    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public NetworkService(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run() { // run the service
        try {
            for (; ; ) {
                pool.execute(new Handler(serverSocket.accept()));
            }
        } catch (IOException ex) {
            pool.shutdown();
        }
    }

    /**
     * 下列方法分两个阶段关闭 ExecutorService。第一阶段调用 shutdown 拒绝传入任务，然后调用
     * shutdownNow（如有必要）取消所有遗留的任务
     *
     * @param pool
     */
    void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

}

class Handler implements Runnable {
    private final Socket socket;

    Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        // read and service request on socket
    }
}

class ThreadFactoryTest {
    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
    }
}
