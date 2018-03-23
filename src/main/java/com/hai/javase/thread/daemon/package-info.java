/**
 * Java线程：线程的调度-守护线程
 * 
 * 守护线程与普通线程写法上基本没什么区别，调用线程对象的方法setDaemon(true)，则可以将其设置为守护线程。
 * 
 * 守护线程使用的情况较少，但并非无用，举例来说，JVM的垃圾回收、内存管理等线程都是守护线程。还有就是在做数据库应用时候，使用的数据库连接池，
 * 连接池本身也包含着很多后台线程，监控连接个数、超时时间、状态等等。
 * 
 * setDaemon方法的详细说明：<br/>
 * public final void setDaemon(boolean on) 将该线程标记为守护线程或用户线程。当正在运行的线程都是守护线程时，Java
 * 虚拟机退出。 该方法必须在启动线程前调用。
 * 
 * 该方法首先调用该线程的 checkAccess 方法，且不带任何参数。这可能抛出 SecurityException（在当前线程中）。
 * 
 * 
 * 参数： on - 如果为 true，则将该线程标记为守护线程。 <br/>
 * 抛出： IllegalThreadStateException - 如果该线程处于活动状态。 <br/>
 * SecurityException - 如果当前线程无法修改该线程。 <br/>
 * 另请参见： isDaemon(), checkAccess()
 * 
 * 实际上：JRE判断程序是否执行结束的标准是所有的前台线程执行完毕了，而不管后台线程【守护线程】的状态，因此，在使用后台线程的时候一定要注意这个问题。
 * 
 * @author Administrator
 * 
 */
package com.hai.javase.thread.daemon;