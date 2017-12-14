/**
 *
 */
package com.hai.ijavase.array.sort;

import java.util.Arrays;

/**
 * 冒泡排序：相邻两元素的循环比较，每轮比较后最值元素都移到数组的最后去了，索引每轮比较后当轮的最后一个元素不用参与下一循环的比较
 *
 * @author Administrator
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        bubbleSort(arr, true);
    }

    public static void bubbleSort(int[] arr, boolean isAsc) {
        int len = arr.length;
        int outCycleLen = len - 1;

        if (isAsc) {
            for (int i = 0; i < outCycleLen; i++) {
                // 注：冒泡排序的内层循环是从0索引开始的
                for (int j = 0; j < outCycleLen - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        } else {
            for (int i = 0; i < outCycleLen; i++) {
                for (int j = 0; j < outCycleLen - i; j++) {
                    if (arr[j] < arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 2, 9, 4, 5, 12, 7};

        BubbleSort.bubbleSort(arr);
        System.out.println("BubbleSort.bubbleSort by asc:\n" + Arrays.toString(arr));

        BubbleSort.bubbleSort(arr, false);
        System.out.println("\nBubbleSort.bubbleSort by desc:\n" + Arrays.toString(arr));
    }
}
