package com.hai.javase.sort;

/**
 * 简单选择排序算法
 * <p>
 * 简单选择的排序的算法思想是：通过n−i次关键字间的比较，从n−i+1个记录中选出关键字最小的记录，并和第i(1≤i≤n)个记录交换之。
 */
public class SelectSort {

    public void selectSort(int[] a) {
        int i, j, min;
        for (i = 0; i < a.length; i++) {
            //假设第一个位置的值是最小值
            min = i;
            for (j = i + 1; j < a.length; j++) {
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            //如果min不等于i，说明找到最小值的下标
            if (min != i) {
                swap(a, i, min);
            }
        }

        for (i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private void swap(int[] a, int i, int min) {
        int temp = a[i];
        a[i] = a[min];
        a[min] = temp;
    }

    public static void main(String[] args) {
        new SelectSort().selectSort(new int[]{9, 1, 5, 8, 3, 7, 4, 6, 2});
    }
}