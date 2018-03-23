package com.hai.javase.thread.threadpool.iexecutors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/12/6.
 */
public class CompletionServiceTest {

    private Executor executor = null;
    private CompletionService completionService = null;
    private List<Future<Integer>> futures = null;
    private int taskCount = 10;

    @Before
    public void before() {
        executor = Executors.newCachedThreadPool();
        completionService = new ExecutorCompletionService(executor);
    }

    @Test
    public void test() {
        futures = submit();
        takeResultByCompletionService();
        takeResultByFuture();
    }

    @After
    public void after() {
        executor = null;
        completionService = null;
        futures = null;
    }

    private void takeResultByCompletionService() {
        System.out.println("take results by completion service:");
        for (int i = 0; i < taskCount; i++) {
//        for (int i = 0; i < taskCount + 2; i++) {
            try {
                Object result = completionService.take().get(2, TimeUnit.SECONDS);
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    private void takeResultByFuture() {
        System.out.println("\ntake results by futures:");
        if (null == futures || futures.size() == 0) {
            System.out.println("no future result.");
            return;
        }
        try {
            for (Future<Integer> future : futures) {
                Integer result = future.get(2, TimeUnit.SECONDS);
                System.out.println(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private List<Future<Integer>> submit() {
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            final int tmp = i;
            Future<Integer> future = completionService.submit(new Callable() {
                @Override
                public Integer call() throws Exception {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000) + 100);
                    return tmp;
                }
            });
            futures.add(future);
        }
        return futures;
    }
}
