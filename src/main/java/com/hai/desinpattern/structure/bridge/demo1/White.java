package com.hai.desinpattern.structure.bridge.demo1;

public class White implements Color{

    public void bepaint(String shape) {
        System.out.println("白色的" + shape);
    }

}