package com.hai.desinpattern.action.visitor;

/**
 * 抽象访问者
 * Created by Administrator on 2018/2/9.
 */
public abstract class Visitor {
    protected String name;

    protected abstract void visitor(MedicineA medicineA);

    protected abstract void visitor(MedicineB medicineB);

    public void setName(String name) {
        this.name = name;
    }
}
