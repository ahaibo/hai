package com.hai.desinpattern.action.visitor;

/**
 * 抽象元素：药品
 * Created by Administrator on 2018/2/9.
 */
public abstract class Medicine {
    protected String name;
    protected double price;

    public Medicine() {
    }

    public Medicine(String name, double price) {
        this.name = name;
        this.price = price;
    }

    protected abstract void accept(Visitor visitor);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
