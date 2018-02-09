package com.hai.ijavase.sort.search;

/*
二分查找算法变种二：斐波那契查找算法

从前面的分析中可以看到，无论划分的关键字太大或者太小都不合适，所以又有人提出了斐波那契查找算法，其利用了黄金分割比原理来实现的。

一个数列如果满足F(n)=F(n-1)+F(n-2)，则称这个数列为斐波那契数列。在斐波那契查找算法中计算mid的公式如下：

mid=low+F(k−1)−1
 */
public class FibonacciSearch {

    public int fibonacciSearch(int[] a, int key) {
        int low = 0, high = a.length - 1, mid = 0, k = 0, i = 0;
        //计算数组的长度的值在斐波那契数列的位置
        while (a.length > F(k) - 1) {
            k++;
        }
        //将不满的数值补全
        int[] newArray = new int[F(k) - 1];
        System.arraycopy(a, 0, newArray, 0, a.length);
        for (i = a.length; i < F(k) - 1; i++)
            newArray[i] = a[a.length - 1];
        a = newArray;
        //查找过程
        while (low <= high) {
            mid = low + F(k - 1) - 1;
            if (key < a[mid]) {
                high = mid - 1;
                k = k - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
                k = k - 2;
            } else {
                if (mid < a.length) {
                    return mid;
                } else {
                    //说明是补全之后的数值
                    return a.length - 1;
                }
            }
        }
        return 0;
    }

    //返回第n项斐波那契数列的值
    private int F(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        int one = 1;
        int two = 0;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = one + two;
            two = one;
            one = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 16, 24, 35, 47, 59, 62, 73, 88, 99};
        int i = new FibonacciSearch().fibonacciSearch(a, 59);
        System.out.println(a[i]);
    }
}