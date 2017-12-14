package com.hai.test.suggest151;

import org.junit.Test;

import java.util.Random;

/**
 * Created by Administrator on 2017/9/28.
 */
public class Sug008_Label {

    @Test
    public void test() {
        m1 : m2(new Random().nextInt());
    }

    public void m1() {
        System.out.println("m1...");
    }

    public void m2(int num) {
        System.out.println("m2: " + num + "...");
    }
}
