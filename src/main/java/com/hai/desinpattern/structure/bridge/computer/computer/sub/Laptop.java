package com.hai.desinpattern.structure.bridge.computer.computer.sub;

import com.hai.desinpattern.structure.bridge.computer.band.Brand;
import com.hai.desinpattern.structure.bridge.computer.computer.Computer;

public class Laptop extends Computer {
    public Laptop(Brand brand) {
        super(brand, "Laptop");
    }
}
