package com.hai.javase.concurrent.complete;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.hai.common.util.ThreadPoolUtils;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description
 *
 * @author hai
 * @date 2021/3/16 15:56
 */
public class GuavaListenableFutureTest {

    private ThreadPoolExecutor orderExecutor = ThreadPoolUtils.newJdkExecutor();

    private ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(orderExecutor);

    Random random = new Random();

    @Test
    public void test() {
        List<ListenableFuture<List>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(listeningExecutorService.submit(() -> {
                List list = new ArrayList();
                try {
                    int sleep = random.nextInt(1000);
                    System.out.println(Thread.currentThread().getName() + "-sleep:" + sleep + " ms");
                    Thread.sleep(sleep);
                    for (int j = 0, len = random.nextInt(20) + 1; j < len; j++) {
                        list.add(j);
                    }
                    System.out.println(Thread.currentThread().getName() + "-list:" + list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return list;
            }));
        }

        Futures.addCallback(Futures.allAsList(futures), new FutureCallback<List<List>>() {
            @Override
            public void onSuccess(@Nullable List<List> result) {
                for (List list : result) {
                    System.out.println(Thread.currentThread().getName() + "-result:" + list);
                }
                System.out.println("done!");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t);
            }
        }, orderExecutor);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
