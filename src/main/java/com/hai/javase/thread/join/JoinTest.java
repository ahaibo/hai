/**
 *
 */
package com.hai.javase.thread.join;

/**
 * Java线程：线程的调度-合并
 * <p>
 * 线程的合并的含义就是将几个并行线程合并为一个单线程执行，应用场景是当一个线程必须等待另一个线程执行完毕才能执行时可以使用join方法。
 * <p>
 * join为非静态方法，定义如下：<br/>
 * void join() 等待该线程终止。 <br/>
 * void join(long millis) 等待该线程终止的时间最长为 millis 毫秒。 <br/>
 * void join(long millis, int nanos) 等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒。
 *
 * @author Administrator
 */
public class JoinTest {
    public static void main(String[] args) {
        Thread childThread = new Thread(new JoinRunnable());
        childThread.start();

        // 主线程循环10次
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程：" + Thread.currentThread().getName() + " 第 " + (i + 1) + " 次执行。");
            if (i > 2) {
                try {
                    // childThread线程合并到主线程中，主线程暂停执行过程，转而执行childThread线程，直到childThread执行完毕后继续。
                    childThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
