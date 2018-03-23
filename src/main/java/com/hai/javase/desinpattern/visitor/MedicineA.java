package com.hai.javase.desinpattern.visitor;

/**
 * 具体元素：药品A
 * Created by Administrator on 2018/2/9.
 */
public class MedicineA extends Medicine {

    public MedicineA(String name, double price) {
        super(name, price);
    }

    @Override
    protected void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
