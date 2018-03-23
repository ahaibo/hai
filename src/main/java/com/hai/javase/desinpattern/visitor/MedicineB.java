package com.hai.javase.desinpattern.visitor;

/**
 * 具体元素：药品B
 * Created by Administrator on 2018/2/9.
 */
public class MedicineB extends Medicine {

    public MedicineB(String name, double price) {
        super(name, price);
    }

    @Override
    protected void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
