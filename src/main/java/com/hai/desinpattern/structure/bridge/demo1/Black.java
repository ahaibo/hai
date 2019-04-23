package com.hai.desinpattern.structure.bridge.demo1;

public class Black implements Color{

    public void bepaint(String shape) {
        System.out.println("黑色的" + shape);
    }
}