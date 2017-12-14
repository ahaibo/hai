package com.hai.test.suggest151;

/**
 * 建议34: 构造函数尽量简化
 * Created by Administrator on 2017/9/28.
 */
public class Sug034_SimpleConstruct {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new SimpleServer(1000);//结果只会输出0或者40000
        }
        /**
         * 原理：
         * <p>
         *     new SimpleServer(1000)
         *     1.1000传递给 SimpleServer 构造函数
         *     2.初始化父类 Server (默认执行super())
         *       a.Server.DEFAULT_PORT 初始化 = 40000
         *       b.执行Server构造函数
         *       c.执行getPort()方法
         *       d.子类的port还未初始化，Server.DEFAULT_PORT 已初始化, 子类的 getPort 方法返回值只能是 0 | Server.DEFAULT_PORT(40000)
         *     3.初始化 SimpleServer port = 1000
         *     4.初始化完成
         * </p>
         */
    }
}

abstract class Server {
    public final static int DEFAULT_PORT = 40000;

    public Server() {
        int port = getPort();
        System.out.println("port: " + port);
    }

    protected abstract int getPort();
}

class SimpleServer extends Server {
    private int port = 100;

    public SimpleServer(int port) {
        this.port = port;
    }

    @Override
    protected int getPort() {
        return Math.random() > 0.5 ? this.port : DEFAULT_PORT;
    }
}
