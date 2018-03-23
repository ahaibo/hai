package com.hai.javase.sort;

/**
 * 直接插入排序算法
 * <p>
 * 直接插入排序算法的思想是：将一个记录插入到已经排序的有序表中，从而得到一个新的、记录数增加1的有序表。
 * 其处理过程是，在排序刚开始的时候，把第一个元素当做是排序的记录，当依次插入后面的元素的时候，就获得其插入的位置，然后形成一个新的有序表。
 */
public class InsertSort {

    public void insertSort(int[] a) {
        int i, j, temp;
        for (i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                temp = a[i];
                for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                    a[j + 1] = a[j];
                }
                a[j + 1] = temp;
            }
        }

        for (i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        new InsertSort().insertSort(new int[]{9, 1, 5, 8, 3, 7, 4, 6, 2});
    }
}