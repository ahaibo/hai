package com.hai.javase.desinpattern.bridge.demo1;

public class Gray implements Color{

    public void bepaint(String shape) {
        System.out.println("灰色的" + shape);
    }
}