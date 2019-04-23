package com.hai.desinpattern.structure.bridge.computer.computer;

import com.hai.desinpattern.structure.bridge.computer.band.Brand;

/**
 * 电脑父类
 */
public abstract class Computer {
    private Brand brand;
    private String name;

    public Computer(Brand brand, String name) {
        this.brand = brand;
        this.name = name;
    }

    public void sale() {
        System.out.println("sale " + brand.getName() + " " + this.name + " computer");
    }

}
