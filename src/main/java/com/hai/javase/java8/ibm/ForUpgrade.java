package com.hai.javase.java8.ibm;

import org.junit.Test;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Java 8 习惯用语，第 3 部分
 * 传统 for 循环的函数式替代方案
 * Created by Administrator on 2017/11/19.
 */
public class ForUpgrade {

    @Test
    public void test1() {
        System.out.print("Get write...");
        for (int i = 1; i < 4; i++) {
            System.out.print(i + "...");
        }
    }

    @Test
    public void testIntStream() {
        System.out.print("Get write...");
        IntStream.range(1, 4)
                .forEach(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value + "...");
                    }
                });
    }

    @Test
    public void testIntStream2() {
        System.out.print("Get write...");
        IntStream.range(1, 4).forEach(i -> System.out.print(i + "..."));

        /**
         *
         我们看到并没有显著减少代码量，但降低了它的复杂性。这样做有两个重要原因：
         1.不同于 for，range 不会强迫我们初始化某个可变变量。
         2.迭代会自动执行，所以我们不需要像循环索引一样定义增量。
         在语义上，最初的 for 循环中的变量 i 是一个可变变量。理解 range 和类似方法的价值对理解该设计的结果很有帮助。
         */
    }

    /**
     * 闭区间
     */
    @Test
    public void testIntStreamRangeClosed() {
        IntStream.rangeClosed(0, 5).forEach(i -> System.out.print(i + "..."));
    }

    /**
     * 跳过值
     */
    @Test
    public void testIntStreamIterate() {
        int sum = IntStream.iterate(1, i -> i + 3).limit(50).sum();
        System.out.println(sum);
    }

//    java9 takeWhile
//    IntStream.iterate(1, e -> e + 3)
//            .takeWhile(i -> i <= 100) //available in Java 9
//            .sum()
}
