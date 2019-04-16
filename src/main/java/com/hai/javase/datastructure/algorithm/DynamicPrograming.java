package com.hai.javase.datastructure.algorithm;

import java.util.Map;

/**
 * 动态规划算法
 */
public class DynamicPrograming {

    /**
     * 动态规划算法(最优)实现
     * 逆向计算
     *
     * @param n
     * @return
     */
    public static int getClimbingWays(int n) {
        if (n < 3) {
            return n < 0 ? 0 : n;
        }
        int a = 1;
        int b = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

    /**
     * 备忘录模式实现动态规划算法
     *
     * @param n
     * @param memorandum
     * @return
     */
    public static int getClimbingWaysByMemorandum(int n, Map<Integer, Integer> memorandum) {
        if (n < 3) {
            return n < 0 ? 0 : n;
        }
        if (memorandum.containsKey(n)) {
            return memorandum.get(n);
        }
        int value = getClimbingWaysByMemorandum(n - 1, memorandum) + getClimbingWaysByMemorandum(n - 2, memorandum);
        memorandum.put(n, value);
        return value;
    }

    /**
     * 递归方式实现动态规划算法
     *
     * @param n
     * @return
     */
    public static int getClimbingWaysByRecursion(int n) {
        if (n < 3) {
            return n < 0 ? 0 : n;
        }
        return getClimbingWaysByRecursion(n - 1) + getClimbingWaysByRecursion(n - 2);
    }

    /**
     * 动态规划算法用例：计算获取的最多的黄金数 TODO
     *
     * @param n 金矿数量
     * @param w 工人数
     * @param g 金矿的黄金量
     * @param p 挖金矿的用工量
     * @return
     */
    public static int getMostGold(int n, int w, int[] g, int[] p) {
        int[] preResults = new int[p.length];
        int[] results = new int[p.length];

        //填充边界格子的值
        for (int i = 0; i <= n; i++) {
            if (i < p[0]) {
                preResults[i] = 0;
            } else {
                preResults[i] = g[0];
            }
        }

        //填充其余格子的值
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (j < p[i]) {
                    results[j] = preResults[i];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j - p[i]] + g[i]);
                }
            }
            preResults = results;
        }

        return results[n];
    }

}
