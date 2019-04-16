package com.hai.javase.datastructure.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Narcissus {
    private static final int CACHE_NUM_LENGTH = 10;
    private static int[] caches = new int[CACHE_NUM_LENGTH];
    private static List<Integer> narcissusList = new ArrayList<>();

    static {
        for (int i = 0; i < CACHE_NUM_LENGTH; i++) {
            caches[i] = (int) Math.pow(i, 3);
        }
        System.out.println(Arrays.toString(caches));
    }

    public static void main(String[] args) {
        narcissus(1000);
    }

    public static void narcissus(int range) {
        long start = System.currentTimeMillis();
        for (int i = 100; i < range; i++) {
            String numStr = String.valueOf(i);
            int result = 0;
            //任意多个(>100)
            for (int j = 0, len = numStr.length(); j < len; j++) {
                result += caches[Integer.valueOf(numStr.charAt(j) + "")];
            }
            //1000以内
//            int a, b, c;
//            a = i / 100;
//            b = (i / 10) % 10;
//            c = i % 10;
//            result = caches[a] + caches[b] + caches[c];
            if (result == i) {
                narcissusList.add(i);
            }
        }
        long end = System.currentTimeMillis();
        long times = end - start;
        System.out.println("used times: " + times + " ms.");

        int size = narcissusList.size();
        System.out.println("narcissusList.size:" + size);
        if (size > 0) {
            System.out.println(Arrays.toString(narcissusList.toArray()));
//            int count = 0;
//            for (Integer num : narcissusList) {
//                System.out.print(num + "\t");
//                if (++count == 10) {
//                    System.out.println();
//                    count = 0;
//                }
//            }
        }
    }
}
