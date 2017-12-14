package com.hai.test.suggest151;

import java.util.zip.DataFormatException;

/**
 * 建议113: 不要在finally块中处理返回值
 * Created by Administrator on 2017/10/1.
 */
public class Sug113_ExceptionFinally {

    public static void main(String[] args) {
        System.out.println();
        try {
            int num1 = doStuff(-1);
            int num2 = doStuff(100);
            System.out.println("num1: " + num1 + "; num2: " + num2);
        } catch (Exception e) {
            System.out.println("never execution...");
        }
    }

    private static int doStuff(int i) {
        try {
            if (i < 0) {
                throw new DataFormatException("数据格式错误");
            } else {
                return i;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            return -1;
        }
    }
}
