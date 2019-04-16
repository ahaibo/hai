package com.hai.javase.datastructure.algorithm;

/**
 * 辗转相除法
 */
public class ZhanZhuanDivide {

    public static int zhanZhuanDivide(int num1, int num2) {
        if (num1 == num2) {
            return num1;
        }
        if (num1 < num2) {
            //保证参数1大于参数2
            return zhanZhuanDivide(num2, num1);
        } else {
            //与1做按位与运算，判断数的奇偶
//            if (!num1 & 1 && !num2 & 1) {
//                return zhanZhuanDivide(num1 >> 1, num2 >> 1);
//            } else if (!num1 & 1 && num2 & 1) {
//                return zhanZhuanDivide(num1 >> 1, num2);
//            } else if (num1 & 1 && !num2 & 1) {
//                return zhanZhuanDivide(num1, num2 >> 1);
//            } else {
//                return zhanZhuanDivide(num1, num1 - num2);
//            }
        }
        return 0;
    }
}
