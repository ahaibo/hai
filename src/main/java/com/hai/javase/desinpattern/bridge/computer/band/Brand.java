package com.hai.javase.desinpattern.bridge.computer.band;

/**
 * 电脑品牌
 */
public abstract class Brand {
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
