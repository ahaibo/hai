package com.hai.javase.datastructure.algorithm;

/**
 * 滑动窗口算法
 */
public class SlideWindow {

    private int age;
    private static int age2;

    public int[] slide(int[] arr, int n, int w) {

        //result数组中保存每个窗口状态下的最大值
        int[] result = new int[n - w + 1];

        //记录双端队列队头的下标 ，队尾下标
        int[] qmax = new int[n];
        int front = 0, back = 0;

        //j 标记是否达到窗口大小,同时记录result中下一个应该放入的元素的下标
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (front < back && arr[qmax[back - 1]] < arr[i])//back为当前要往qmax中放入的值
                back--;

            qmax[back++] = i;

            if (j + w - 1 == i) {
                //达到窗口长度
                result[j] = arr[qmax[front]];
                j++;
            }

            if (qmax[front] + w - 1 == i) {
                //队头过期
                front++;
            }
        }

        return result;

    }

    static class A {
        void p() {
            System.out.println(age2);
        }

        static void p2() {
            System.out.println(age2);
        }
    }

}