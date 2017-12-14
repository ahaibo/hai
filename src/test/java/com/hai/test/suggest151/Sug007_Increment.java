package com.hai.test.suggest151;

import org.junit.Test;

/**
 * 建议07: 警惕自增的陷阱
 * Created by Administrator on 2017/9/28.
 */
public class Sug007_Increment {

    @Test
    public void test1() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count = count++;
        }
        System.out.println(count);//0
        /**JVM此操作的步骤
         *
         * 1.JVM把count的值copy到临时区
         * 2.count值加1
         * 3.返回临时区变量，值为0，没修改过
         * 4.把临时区返回的值赋值给count
         *
         * code：
         * public int mockAdd(int count){
         *     int temp=count;
         *     count=count + 1;
         *     return temp;
         * }
         */
    }

    @Test
    public void test2() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count++;
        }
        System.out.println(count);
    }

    @Test
    public void test3() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count = ++count;
        }
        System.out.println(count);
    }

    @Test
    public void test4() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            ++count;
        }
        System.out.println(count);
    }
}
