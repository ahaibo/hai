package com.hai.javase.desinpattern.bridge.computer.computer.sub;

import com.hai.javase.desinpattern.bridge.computer.band.Brand;
import com.hai.javase.desinpattern.bridge.computer.computer.Computer;

public class Desktop extends Computer {
    public Desktop(Brand brand) {
        super(brand, "Desktop");
    }
}
