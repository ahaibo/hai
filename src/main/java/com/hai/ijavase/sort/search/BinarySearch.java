package com.hai.ijavase.sort.search;

/**
 * 二分查找算法也称为折半查找算法，是一种在查找算法中普遍使用的算法。
 * <p>
 * 其算法的基本思想是：
 * 在有序表中，取中间的记录作为比较关键字，若给定值与中间记录的关键字相等，则查找成功；
 * 若给定的值小于中间记录的关键字，则在中间记录的左半区间继续查找；
 * 若给定值大于中间记录的关键字，则在中间记录的右半区间继续查找；
 * 不断重复这个过程，直到查找成功。否则查找失败。
 * Created by Administrator on 2018/2/9.
 */
public class BinarySearch {
    public int binarySearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] == key) return mid;
            if (a[mid] > key) high = mid - 1;
            if (a[mid] < key) low = mid + 1;
        }
        return -1;
    }
}
