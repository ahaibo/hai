package com.hai.test;

import org.junit.Test;

public class RuntimeTest {

    @Test
    public void test1() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
