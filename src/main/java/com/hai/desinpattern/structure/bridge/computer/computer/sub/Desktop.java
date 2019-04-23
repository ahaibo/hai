package com.hai.desinpattern.structure.bridge.computer.computer.sub;

import com.hai.desinpattern.structure.bridge.computer.band.Brand;
import com.hai.desinpattern.structure.bridge.computer.computer.Computer;

public class Desktop extends Computer {
    public Desktop(Brand brand) {
        super(brand, "Desktop");
    }
}
