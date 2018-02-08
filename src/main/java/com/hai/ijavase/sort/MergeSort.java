package com.hai.ijavase.sort;

/**
 * 归并排序
 */
/*
归并排序是所有常用内部排序算法中稳定性最好的，无论是平均时间复杂度、最坏时间复杂度还是最好时间复杂度，其时间复杂度都是O(nlogn)。
由于这个特性，在需要考虑排序稳定性的情况下，归并排序是所有优化算法（直接插入排序、冒泡排序和简单选择排序）使用最多的。

其实归并排序算法的思想很简单：假设初始序列含有n个记录，则可以看成是n个有序的子序列，每一个子序列的长度都是1，
然后把这些子序列两两归并，得到⌈n/2⌉（⌈x⌉表示不小于x的最小整数）个长度为2或者1的有序子序列；再两两归并，……，如此重复，直至得到一个长度为n的有序序列为止。
这种方法也被称为2路归并排序。
 */
public class MergeSort {

    public void mergeSort(int[] a) {
        mSort(a, a, 0, a.length - 1);

        //print sort
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "\t");
        }
    }

    private void mSort(int[] b, int[] a, int i, int j) {
        int m = 0;
        int[] c = new int[a.length];
        if (i == j) {//递归终止条件
            a[i] = b[i];
        } else {
            m = (i + j) / 2;
            //将数组a分为a[i...m]并进行排序
            mSort(b, c, i, m);
            //数组a[m+1...j]部分进行排序
            mSort(b, c, m + 1, j);
            //将a[i...m]部分和a[m+1...j]部分的排序结果归并到a
            merge(c, a, i, m, j);
        }
    }

    private void merge(int[] b, int[] a, int i, int m, int t) {
        int j = 0, k = 0, l = 0;
        for (j = m + 1, k = i; i <= m && j <= t; k++) {
            //将b中记录由小到大归并到a中
            if (b[i] < b[j]) {
                a[k] = b[i++];
            } else {
                a[k] = b[j++];
            }
        }
        //将剩余b[i...m]复制到a中
        if (i <= m) {
            for (l = 0; l <= m - i; l++) {
                a[k + l] = b[i + l];
            }
        }
        //将剩余b[m+1...t]复制到数组a中
        if (j <= t) {
            for (l = 0; l <= t - j; l++) {
                a[k + l] = b[j + l];
            }
        }
    }

    public static void main(String[] args) {
        new MergeSort().mergeSort(new int[]{50, 10, 90, 30, 70, 40, 80, 60, 20});
    }
}