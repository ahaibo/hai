package com.hai.ijavase.thread.sync.exchange;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchange
 * 用于实现两个数据之间的交换，交换过程中先拿出数据的一方会一直等待另一方的数据到来，然后互换
 * Created by Administrator on 2017/12/6.
 */
public class ExchangeTest {

    public void test() {
        ExecutorService service = Executors.newCachedThreadPool();
        Exchanger<String> exchanger = new Exchanger();

        for (int i = 1; i <= 2; i++) {
            final int temp = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    String data1 = "data" + temp;
                    String thread = Thread.currentThread().getName();
                    System.out.println("thread:\t" + thread + "\tpre-exchange data:\t" + data1);
                    String data2 = exchange(exchanger, data1);
                    System.out.println("thread:\t" + thread + "\texchanged data:\t" + data2);
                }
            });
        }

        service.shutdown();

    }

    private <T> T exchange(Exchanger<T> exchanger, T data) {
        try {
            Thread.sleep(new Random().nextInt(5000) + 200);
            return exchanger.exchange(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new ExchangeTest().test();
    }
}
