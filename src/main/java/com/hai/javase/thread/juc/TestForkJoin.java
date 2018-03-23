package com.hai.javase.thread.juc;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by Administrator on 2018/3/23.
 */
public class TestForkJoin {
    private static final long COUNT_SUM = 100000000 * 500L;
    private static final long THRESHOLD = 100000;
    private SumRecursiveTesk sumTask;

    @Before
    public void before() {
//        sumTask = this.new SumRecursiveTesk(0, COUNT_SUM);
    }

    @Test
    public void test1() {
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0L; i <= COUNT_SUM; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(" val: " + COUNT_SUM);
        System.out.println(" sum: " + sum);
        System.out.println("time: " + Duration.between(start, end).toMillis());
    }

    //JDK8 特性
    @Test
    public void testByLongStream() {
        Instant start = Instant.now();
        Long sum = LongStream
                .rangeClosed(0L, COUNT_SUM)
                .parallel()
                .reduce(0L, Long::sum);

        Instant end = Instant.now();
        System.out.println(" val: " + COUNT_SUM);
        System.out.println(" sum: " + sum);
        System.out.println("time: " + Duration.between(start, end).toMillis());
    }

    @Test
    public void testForkJoin() {
        Instant start = Instant.now();

        //使用 ForkJoin 一般步骤
        //1.继承 RecursiveTask 实现业务
        //2.使用 ForkJoinPool 执行 Task
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = this.new SumRecursiveTesk(0, COUNT_SUM);
        long sum = pool.invoke(task);

        Instant end = Instant.now();
        System.out.println(" val: " + COUNT_SUM);
        System.out.println(" sum: " + sum);
        System.out.println("time: " + Duration.between(start, end).toMillis());
    }

    public class SumRecursiveTesk extends RecursiveTask<Long> {
        private long start;
        private long end;

        public SumRecursiveTesk(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            long length = end - start;
            if (length <= THRESHOLD) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                long middle = (start + end) / 2;
                SumRecursiveTesk left = new SumRecursiveTesk(start, middle);
                left.fork();//进行拆分，同时压入线程队列

                SumRecursiveTesk right = new SumRecursiveTesk(middle + 1, end);
                right.fork();

                sum = left.join() + right.join();
            }
            return sum;
        }
    }
}
