package com.hai.javase.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 统计一个数中指定数出现的次数
 * Created by Administrator on 2018/4/19.
 */
public class CountNumber {

    @Test
    public void count1_1() {
        int n = 7;
        System.out.println("number: " + n);

        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        System.out.println("count: " + count);
    }

    @Test
    //针对count1_1的简化
    public void count1_2() {
        int n = 7;
        System.out.println("number: " + n);

        int count = 0;
        for (count = 0; n > 0; n >>= 1) {
            count += n & 1;
        }
        System.out.println("count: " + count);
    }

    @Test
    //快速法
    //这种方法速度比较快，其运算次数与输入n的大小无关，只与n中1的个数有关。如果n的二进制表示中有k个1，那么这个方法只需要循环k次即可。
    // 其原理是不断清除n的二进制表示中最右边的1，同时累加计数器，直至n为0
    public void count1_3() {
        int n = 7;
        System.out.println("number: " + n);

        int count = 0;
        for (count = 0; n > 0; ++count) {
            n &= (n - 1);
        }
        System.out.println("count: " + count);
    }

    @Test
    public void testFind() {
        int target = 5;
        int[][] array = new int[][]{
                new int[]{1, 2, 8, 9},
                new int[]{2, 4, 9, 12},
                new int[]{4, 7, 10, 13},
                new int[]{6, 8, 11, 15}};
        find(target, array);
    }

    public boolean find(int target, int[][] array) {
        int min = array[0][0];
        int[] last = array[array.length - 1];
        int max = last[last.length - 1];
        boolean include = false;
        System.out.println("array: " + Arrays.toString(array));
        System.out.println("target: " + target);
        System.out.println("min: " + min);
        System.out.println("max: " + max);


//        include = target >= min && target <= max;
        include = min <= target && target <= max;
        System.out.println("is include: " + include);

        ArrayList<Integer> list = new ArrayList<>();

        return include;
    }

    public void reOrderArray(int[] array) {
        int len = array.length;
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            if (array[i] % 2 != 0) {
                list1.add(array[i]);
            } else {
                list2.add(array[i]);
            }
        }
        list1.addAll(list2);
        for (int i = 0, length = list1.size(); i < length; i++) {
            array[i] = list1.get(i);
        }
    }

    /**
     * 计数排序
     *
     * @param array 待排序数组(假定各元素的范围是0~max，包括0和max)
     * @param max   待排序数组中的最大值
     */
    public int[] countingSort(int[] array, int max) {
        int[] result = new int[array.length];
        int[] temp = new int[max + 1];

        // 以下循环操作完成后，temp的第i个位置保存着array中，值为i的元素的总个数
        for (int i : array) {
            temp[i]++;
        }

        // 以下循环操作完成后，temp的第i个位置保存着array中，值小于或等于i的元素的总个数
        for (int i = 1; i < temp.length; i++) {
            temp[i] += temp[i - 1];
        }

        for (int i = array.length - 1; i > -1; i--) {
            result[temp[array[i]] - 1] = array[i];
            temp[array[i]]--;
        }

        return result;
    }

    @Test
    public void testCountingSort() {
        int[] array = {2, 5, 3, 0, 2, 3, 0, 3, 1, 2, 3, 5, 9, 7, 8, 6, 10};
        System.out.println(Arrays.toString(countingSort(array, 10)));
    }

}
