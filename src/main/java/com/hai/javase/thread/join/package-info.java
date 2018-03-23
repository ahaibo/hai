/**
 * Java线程：线程的调度-合并
 * 
 * 线程的合并的含义就是将几个并行线程合并为一个单线程执行，应用场景是当一个线程必须等待另一个线程执行完毕才能执行时可以使用join方法。
 * 
 * join为非静态方法，定义如下：<br/>
 * void join() 等待该线程终止。 <br/>
 * void join(long millis) 等待该线程终止的时间最长为 millis 毫秒。 <br/>
 * void join(long millis, int nanos) 等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒。
 * 
 * @author Administrator
 * 
 */
package com.hai.javase.thread.join;