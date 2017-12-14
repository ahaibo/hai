package com.hai.ijavase.thread.threadpool.iexecutors;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 带有返回值的线程
 * <p>
 * 在Java5之前，线程是没有返回值的，常常为了“有”返回值，破费周折，而且代码很不好写。或者干脆绕过这道坎，走别的路了。
 * <p>
 * 现在Java终于有可返回值的任务（也可以叫做线程）了。
 * <p>
 * 可返回值的任务必须实现Callable接口，类似的，无返回值的任务必须实现Runnable接口。
 * <p>
 * 执行Callable任务后，可以获取一个Future的对象，在该对象上调用get就可以获取到Callable任务返回的Object了。
 * <p>
 * 而以实现Runnable接口执行submit的结果Future调用其get方法不会返回结果
 *
 * @author Administrator
 */
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // executorService.submit by Callable
        // 以实现Callable接口执行submit的结果Future调用其get方法会返回结果

        Callable<Object> callable1 = new FutureByCallable(10, "callable1");
        Callable<Object> callable2 = new FutureByCallable(20, "callable2");
        Callable<Object> callable3 = new FutureByCallable(30, "callable3");
        Callable<Object> callable4 = new FutureByCallable(40, "callable4");
        Callable<Object> callable5 = new FutureByCallable(50, "callable5");

        // executorService.submit by Runnable
        // 以实现Runnable接口执行submit的结果Future调用其get方法不会返回结果

        Runnable runnable1 = new FutureByRunnable(60, "runnable1");
        Runnable runnable2 = new FutureByRunnable(70, "runnable2");
        Runnable runnable3 = new FutureByRunnable(80, "runnable3");
        Runnable runnable4 = new FutureByRunnable(90, "runnable4");
        Runnable runnable5 = new FutureByRunnable(100, "runnable5");

        Future<Object> future1 = executorService.submit(callable1);
        Future<Object> future2 = executorService.submit(callable2);
        Future<Object> future3 = executorService.submit(callable3);
        Future<Object> future4 = executorService.submit(callable4);
        Future<Object> future5 = executorService.submit(callable5);

        Future<?> future6 = executorService.submit(runnable1);
        Future<?> future7 = executorService.submit(runnable2);
        Future<?> future8 = executorService.submit(runnable3);
        Future<?> future9 = executorService.submit(runnable4);
        Future<?> future10 = executorService.submit(runnable5);

        try {
            System.out.println("result1: " + future1.get());
            System.out.println("result2: " + future2.get());
            System.out.println("result3: " + future3.get());
            System.out.println("result4: " + future4.get());
            System.out.println("result5: " + future5.get());
            System.out.println("result6: " + future6.get());
            System.out.println("result7: " + future7.get());
            System.out.println("result8: " + future8.get());
            System.out.println("result9: " + future9.get());
            System.out.println("result10: " + future10.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}

class FutureByCallable implements Callable<Object> {

    private int id;
    private String name;

    public FutureByCallable() {
    }

    /**
     * @param id
     * @param name
     */
    public FutureByCallable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public Object call() throws Exception {
        System.out.println(this.name + " call, id: " + this.id);
        return MyBusiness.business(this.id);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FutureByCallable [id=" + id + ", name=" + name + "]";
    }

}

class FutureByRunnable implements Runnable {
    private int id;
    private String name;

    public FutureByRunnable() {
    }

    /**
     * @param id
     * @param name
     */
    public FutureByRunnable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        System.out.println(this.name + " run, id: " + this.id);
        MyBusiness.business(this.id);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FutureByRunnable [id=" + id + ", name=" + name + "]";
    }

}

/**
 * 模拟场景类
 *
 * @author Administrator
 */
class MyBusiness {
    /**
     * 模拟业务场景：简单的做乘法计算
     *
     * @param radix
     * @return
     */
    public static Object business(int radix) {
        int minNum = 18, maxNum = 100;
        int randomNum = random(minNum, maxNum);

        return randomNum * Math.abs(radix);
    }

    /**
     * 生成指定区间的随机数
     *
     * @param minNum
     * @param maxNum
     * @return
     */
    private static int random(int minNum, int maxNum) {
        Random random = new Random();
        int result = 0;
        do {
            result = random.nextInt(maxNum);
        }
        while (result < minNum);

        return result;
    }
}
