package com.hai.ijavase.sort;

import java.util.Arrays;

/*
冒泡排序
冒泡排序的基本思想是两两比较相邻记录的关键字，如果反序就交换，直到没有反序的关键字为止。
 */
public class BubbleSort {

    public void bubbleSort1(int[] a) {
        int i, j, len = a.length;
        for (i = 0; i < len; i++) {
            for (j = i + 1; j < len; j++) {
                if (a[i] > a[j]) {
                    swap(a, i, j);
                }
            }
        }
    }

    /**
     * 针对第一种排序，在排好第一个和第二个为止之后，数字3反而被排到了最后面。下面是针对这种情况的改良版算法
     * 这里的改进主要把第二个for循环由从前往后比较改成由后往前进行比较了，这样的好处是可以把本来较小的元素放在尽可能前一点的位置，这种差异性在数据量较大的时候能够体现出来。
     *
     * @param a
     */
    public void bubbleSort2(int[] a) {
        int i, j, len = a.length;
        for (i = 0; i < len; i++) {
            for (j = len - 2; j >= i; j--) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    /**
     * bubbleSort2 的冒泡排序使用于基本无序的序列，如果是基本有序的序列再使用上述的算法进行排序就会出现一个问题：
     * 那就是可能在进行完前几次的冒泡之后就已经是有序的了，那么后面的冒泡都是多余的。下面得代码是针对这种情况进行的优化
     *
     * @param a
     */
    public void bubbleSort3(int[] a) {
        int i, j, len = a.length;
        boolean flag = true;
        for (i = 0; i < len && flag; i++) {
            flag = false;
            for (j = len - 2; j >= i; j--) {
                if (a[j] > a[j + 1]) {//如果不进行数据交换，说明是有序的
                    swap(a, j, j + 1);
                    flag = true;
                }
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{9, 1, 5, 8, 3, 7, 4, 6, 2, 22, 10, 55, 99, 12, 19, 100};
        BubbleSort bubbleSort = new BubbleSort();
        System.out.println("before sort: " + Arrays.toString(array));
        bubbleSort.bubbleSort1(array);
        System.out.println("after sort with bubbleSort1: " + Arrays.toString(array));
        bubbleSort.bubbleSort2(array);
        System.out.println("after sort with bubbleSort2: " + Arrays.toString(array));
        bubbleSort.bubbleSort3(array);
        System.out.println("after sort with bubbleSort3: " + Arrays.toString(array));
    }
}