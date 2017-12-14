package com.hai.test;

/*
输出所有的水仙花数，所谓水仙花数是指一个3位数，其各个位上数字立方和等于其本身。
例如： 153 = 1*1*1 + 3*3*3 + 5*5*5 
*/
class ShuiXianHua {
    public static void main(String[] args) {
        System.out.println("所有水仙花数为：");
        for (int i = 100; i < 1000; i++) {//实现所有的三位数的一个遍历
            if (isShuiXianHua(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isShuiXianHua(int num) {
        int j1 = 0;
        int j2 = 0;
        int j3 = 0;
        j1 = num / 100;//百位
        j2 = (num - 100 * j1) / 10;//十位
        j3 = num - 100 * j1 - 10 * j2;//个位

        if (num == j1 * j1 * j1 + j2 * j2 * j2 + j3 * j3 * j3) {
            return true;
        }
        return false;
    }
}