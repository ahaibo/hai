package com.hai.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author hai
 * @date 2020/12/5 12:10
 */
@Slf4j
public class ThreadPoolUtils {

    static int DEFAULT_CORE_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    static int MAX_POOL_SIZE = DEFAULT_CORE_SIZE * 10;
    static int MAX_QUEUE_SIZE = MAX_POOL_SIZE * 20;
    /**
     * 允许线程空闲时间（单位：默认为秒）等待60S后，还没有任务需要处理，那么进行线程回收处理
     */
    private static final int KEEP_ALIVE_TIME = 60;

    public static ThreadPoolExecutor newJdkExecutor() {
        LinkedBlockingQueue workQueue = new LinkedBlockingQueue(MAX_QUEUE_SIZE);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(DEFAULT_CORE_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue);
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        log.info("自定义 bunchexecutor 线程池初始化成功，核心线程:[{}]，最大[{}]", DEFAULT_CORE_SIZE, MAX_POOL_SIZE);
        return executor;
    }
}
