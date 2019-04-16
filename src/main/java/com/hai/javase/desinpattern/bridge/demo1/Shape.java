package com.hai.javase.desinpattern.bridge.demo1;

public abstract class Shape {
    Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw();
}