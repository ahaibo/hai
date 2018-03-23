package com.hai.javase.desinpattern.mediator;

/**
 * Created by Administrator on 2018/2/8.
 */
public class HouseOwner extends Person {

    public HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    protected void message(String msg) {
        System.out.println(this.getClass().getName() + " 房主 " + this.name + " read message: " + msg);
    }
}
