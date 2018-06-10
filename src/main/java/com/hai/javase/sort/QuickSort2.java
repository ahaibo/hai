package com.hai.javase.sort;

/**
 * 快速排序，顾名思义，是一种速度快，效率高的排序算法。
 * 快排原理：
 * 在要排的数（比如数组A）中选择一个中心值key（比如A[0]），通过一趟排序将数组A分成两部分，其中以key为中心，key右边都比key大，key左边的都key小，然后对这两部分分别重复这个过程，直到整个有序。
 * 整个快排的过程就简化为了一趟排序的过程，然后递归调用就行了。
 * 一趟排序的方法：
 * 1，定义i=0，j=A.lenght-1，i为第一个数的下标，j为最后一个数下标
 * 2，从数组的最后一个数Aj从右往左找，找到第一小于key的数，记为Aj；
 * 3，从数组的第一个数Ai 从左往右找，找到第一个大于key的数，记为Ai；
 * 4，交换Ai 和Aj
 * 5，重复这个过程，直到 i=j
 * 6，调整key的位置，把A[i] 和key交换
 * https://blog.csdn.net/Yexiaofen/article/details/78018204
 * <p>
 * Created by Administrator on 2018/4/17.
 */
public class QuickSort2 {
    public static void main(String[] args) {
        System.out.println();
    }

    public static void quickSort(int[] arr, int low, int high) {

        //1.找到递归算法的出口
        if (low > high) {
            return;
        }

        //2.存值
        int i = low;
        int j = high;

        //3.key
        int key = arr[low];

        //4.完成一趟排序
        while (i < j) {
            //4.1 从右往左找到第一个小于key的数
            while (i < j && arr[j] < key) {
                j--;
            }

            //4.2 从左往右找到第一个大于key的数
            while (i < j && arr[i] <= key) {
                i++;
            }

            //4.3 交换
            if (i < j) {
                int p = arr[i];
                arr[i] = arr[j];
                arr[j] = p;
            }
        }

        // 4.4，调整key的位置
        int p = arr[i];
        arr[i] = arr[low];
        arr[low] = p;

        //5, 对key左边的数快排
        quickSort(arr, low, i - 1);

        //6, 对key右边的数快排
        quickSort(arr, i + 1, high);
    }
}
