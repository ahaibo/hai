package com.hai.ijavase.desinpattern.mediator;

/**
 * Created by Administrator on 2018/2/8.
 */
public class Tenant extends Person {

    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    protected void message(String msg) {
        System.out.println(this.getClass().getName() + " 租房者 " + this.name + " get message: " + msg);
    }
}
