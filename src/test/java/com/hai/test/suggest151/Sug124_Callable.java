package com.hai.test.suggest151;

import org.junit.Test;

import java.util.Calendar;
import java.util.concurrent.*;

/**
 * 建议124: 异步运算考虑使用callable接口
 * Created by Administrator on 2017/10/2.
 */
public class Sug124_Callable {

    @Test
    public void test() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new TaxCalculator(1000));

        while (!future.isDone()) {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.print(".");
        }
        System.out.println("\nfuture is done. result: " + future.get());

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.print(".");
        }
        System.out.println("\nexecutorService is terminated...");

    }

    @Test
    public void test2() {
        System.out.println(Calendar.getInstance().get(13));
        System.out.println(Calendar.getInstance().get(Calendar.SECOND));
    }

    class TaxCalculator implements Callable<Integer> {
        private int seedMoney;

        public TaxCalculator(int seedMoney) {
            this.seedMoney = seedMoney;
        }

        @Override
        public Integer call() throws Exception {
            TimeUnit.MILLISECONDS.sleep(5000);
            return seedMoney / 10;
        }

        public void calc(){
        }
    }
}
