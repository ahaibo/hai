package com.hai.javase.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Administrator on 2017/9/10.
 */
public class CommonUtil {
    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            int2bytes(new Random().nextInt(100));
        }
    }

    /**
     * 将整数转换为字节数组
     * 原理：int型占4字节、32位，byte占1字节8位，所以每次arr执行后其值移动一字节的位置即8位
     *
     * @param i
     * @return
     */
    public byte[] int2bytes(int i) {

        System.out.println("num: " + i);
        byte[] arr = new byte[4];
        arr[0] = (byte) i;
        arr[1] = (byte) (i >> 8);
        arr[2] = (byte) (i >> 16);
        arr[3] = (byte) (i >> 24);
        System.out.println("arr: " + Arrays.toString(arr));
        return arr;
    }

    /**
     * 将字节数组转换为整数
     * 原理：与int2bytes相反
     *
     * @param arr
     * @return
     */
    public int bytes2int(byte[] arr) {
        System.out.println("arr: " + Arrays.toString(arr));
        int i0 = arr[3] << 24;
        int i1 = (arr[2] & 0xff) << 16;
        int i2 = (arr[1] & 0xff) << 8;
        int i3 = (arr[0] & 0xff);
        int i = i0 | i1 | i2 | i3;
        System.out.println("num: " + i);
        return i;
    }
}
