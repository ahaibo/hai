package com.hai.javase.thread.juc;

import java.util.concurrent.*;

/**
 * 一、创建执行线程的方式三：实现 Callable 接口。 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
 * 二、执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。  FutureTask 是  Future 接口的实现类
 * Created by Administrator on 2018/3/23.
 */
public class TestCallable {

    public static void main(String[] args) {
        CallableThreadDemo threadDemo = new CallableThreadDemo();

        //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Integer> result = new FutureTask<Integer>(threadDemo);
        new Thread(result).start();

        //2.接收线程运算后的结果
        try {
            Integer sum = result.get(5L, TimeUnit.SECONDS);//FutureTask 可用于 闭锁
            System.out.println("sum: " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

class CallableThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        return sum;
    }
}
