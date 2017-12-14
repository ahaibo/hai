/**
 *
 */
package com.hai.ijavase.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列是Java5线程新特征中的内容，Java定义了阻塞队列的接口java.util.concurrent.BlockingQueue，阻塞队列的概念是
 * ，一个指定长度的队列，如果队列满了，添加新元素的操作会被阻塞等待，直到有空位为止。同样，当队列为空时候，请求队列元素的操作同样会阻塞等待，
 * 直到有可用元素为止。
 * <p>
 * 有了这样的功能，就为多线程的排队等候的模型实现开辟了便捷通道，非常有用。
 *
 * @author Administrator
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(20, true);

        for (int i = 0; i < 21; i++) {
            try {
                blockingQueue.put(i);
                System.out.println("向阻塞队列BlockingQueue中添加了元素:" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("程序到此运行结束，即将退出----");
    }
}
