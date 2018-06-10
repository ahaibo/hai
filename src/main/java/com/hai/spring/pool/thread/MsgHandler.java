package com.hai.spring.pool.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MsgHandler implements Runnable {


    @Autowired
    private ThreadsPoolConfig config;  // 注入 配置

    @Override
    public void run() {
        // do 这里 写 处理的逻辑
        System.out.println("创建线程 处理事务....");
    }


    @PostConstruct
    public void loadThreadsPool() {

        // 初始化 线程池
        HandlerThreadsPool handlerThreadsPool = new HandlerThreadsPool(config);

        //调用线程池，创建线程  。处理事件
        handlerThreadsPool.execute(new MsgHandler());
    }
}