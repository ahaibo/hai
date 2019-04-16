package com.hai.javase.datastructure.algorithm;

import org.junit.Test;

/**
 * Created by Administrator on 2018/4/3.
 */
public class AlgorithmLevel1 {

    final static MyNumber MY_NUMBER = new AlgorithmLevel1().new MyNumber(1);

    class MyNumber {
        int value;

        public MyNumber() {
        }

        public MyNumber(int value) {
            this.value = value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public int reverseInteger(int n) {
        int res = 0;
        while (n != 0) {
            int val = n % 10;
            int tmp = res;
            res = tmp * 10 + val;
            n = n / 10;
            if (res / 10 != tmp) return 0;
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(reverseInteger(-1234567890));
    }

    @Test
    public void test2() {
        System.out.println("before: " + MY_NUMBER.getValue());
        MY_NUMBER.setValue(2);
        System.out.println("after: " + MY_NUMBER.getValue());
    }

}
