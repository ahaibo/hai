package com.hai.javase.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Administrator on 2017/9/8.
 */
public class ParallelArrays {

    @Test
    public void test() {
        //init
        long[] arr = new long[20000];
        //set value
        Arrays.parallelSetAll(arr, index -> ThreadLocalRandom.current().nextInt(1000000));
        //display top 100
        Arrays.stream(arr).limit(100).forEach(i -> System.out.print(i + "\t"));
        System.out.println();

        //sort
        Arrays.parallelSort(arr);
        //display top 100 after sort...
        Arrays.stream(arr).limit(100).forEach(i -> System.out.print(i + "\t"));
    }
}
