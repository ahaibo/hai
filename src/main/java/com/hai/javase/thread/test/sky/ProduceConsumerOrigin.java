package com.hai.javase.thread.test.sky;

/**
 * 现程序中的代码在不断产生数据，然后交给 ProduceConsumerDo.doSome 方法处理，类似生产者不断产生数据，消费者在不断消费数据。
 * 请将程序改造成由10个线程来消费生产者的数据，这些消费者都调用ProduceConsumerDo.doSome 方法处理，故每个消费者都需要1秒才能处理完，
 * 程序应保证这些消费者依次有序的进行消费，只有上一个消费者消费完后，下一个消费者才能消费。下一个消费者是谁都行，但要保证这些消费者线程拿到的数据有序
 * <p>
 * Created by Administrator on 2017/12/6.
 */
public class ProduceConsumerOrigin {
    public static void main(String[] args) {
        System.out.println("begin: " + (System.currentTimeMillis() / 1000));
        for (int i = 0; i < 10; i++) {//此行代码不能修过
            String input = i + "";//此行代码不能修过
            String output = ProduceConsumerDo.doSome(input);
            System.out.println(Thread.currentThread().getName() + ":" + output);
        }
    }
}

//此类不能修过
class ProduceConsumerDo {
    public static String doSome(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return input + ":" + (System.currentTimeMillis() / 1000);
    }
}
