package com.hai.javase.concurrent.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by Administrator on 2018/10/22.
 */
public class AtomicTest {

    public void test1() {
        AtomicStampedReference stampedReference = new AtomicStampedReference(1, 1);
//        boolean result = stampedReference.compareAndSet(1, 2, 1);
    }
}
