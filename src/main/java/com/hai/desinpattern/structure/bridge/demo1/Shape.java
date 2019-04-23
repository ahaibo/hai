package com.hai.desinpattern.structure.bridge.demo1;

public abstract class Shape {
    Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw();
}