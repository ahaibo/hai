package com.hai.desinpattern.structure.bridge.computer.computer.sub;

import com.hai.desinpattern.structure.bridge.computer.band.Brand;
import com.hai.desinpattern.structure.bridge.computer.computer.Computer;

public class Pad extends Computer {
    public Pad(Brand brand) {
        super(brand, "Pad");
    }
}
