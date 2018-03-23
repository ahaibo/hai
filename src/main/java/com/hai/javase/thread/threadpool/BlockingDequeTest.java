/**
 *
 */
package com.hai.javase.thread.threadpool;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 对于阻塞栈，与阻塞队列相似。不同点在于栈是“后入先出”的结构，每次操作的是栈顶，而队列是“先进先出”的结构，每次操作的是队列头。
 * <p>
 * 这里要特别说明一点的是，阻塞栈是Java6的新特征。、
 * <p>
 * Java为阻塞栈定义了接口：java.util.concurrent.BlockingDeque，所有已知实现类： LinkedBlockingDeque
 *
 * @author Administrator
 */
public class BlockingDequeTest {
    public static void main(String[] args) {
        int capacity = 20;
        BlockingDeque<Object> blockingDeque = new LinkedBlockingDeque<>(capacity);

        int size = capacity + 1;
        for (int i = 0; i < size; i++) {
            try {
                blockingDeque.put(i);
                System.out.println("向阻塞栈BlockingDeque中添加了元素:" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("程序到此运行结束，即将退出----");
    }
}
