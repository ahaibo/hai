package com.hai.test;

import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public class TestDemo {

    @Test
    public void testRuntime() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors: " + availableProcessors);
    }

    @Test
    public void testString() {
        String s1 = "ab";
        String s2 = "a" + "b";
        System.out.println(s1 == s2);
    }

    @Test
    public void change() {
        int n = -55, m = 132;
        //int temp = n;
        //n = m;
        //m = temp;
        System.out.println("n=" + n + ",m=" + m);

        //n = n + m;
        //m = n - m;
        //n = n - m;
        System.out.println("n=" + n + ",m=" + m);

        n = n ^ m;
        m = n ^ m;
        n = n ^ m;
        System.out.println("n=" + n + ",m=" + m);

        char c = '海';
        System.out.println(c);
    }

    /*public <E> void read(List<? super E> list) {
        for (E e : list) {

        }
    }*/
    public <E> void read(List<? extends E> list) {
        for (E e : list) {

        }
    }

    /*public void write(List<? extends Number> list) {
        list.add(123);
    }*/

    public void write(List<? super Number> list) {
        list.add(123);
    }
}
