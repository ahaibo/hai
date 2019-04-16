package com.hai.javase.desinpattern.bridge.computer.computer.sub;

import com.hai.javase.desinpattern.bridge.computer.band.Brand;
import com.hai.javase.desinpattern.bridge.computer.computer.Computer;

public class Laptop extends Computer {
    public Laptop(Brand brand) {
        super(brand, "Laptop");
    }
}
