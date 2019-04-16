package com.hai.javase.desinpattern.bridge.computer.computer.sub;

import com.hai.javase.desinpattern.bridge.computer.band.Brand;
import com.hai.javase.desinpattern.bridge.computer.computer.Computer;

public class Pad extends Computer {
    public Pad(Brand brand) {
        super(brand, "Pad");
    }
}
