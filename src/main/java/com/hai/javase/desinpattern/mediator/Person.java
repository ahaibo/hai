package com.hai.javase.desinpattern.mediator;

/**
 * Created by Administrator on 2018/2/8.
 */
public abstract class Person {
    protected String name;
    protected Mediator mediator;

    public Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    protected abstract void message(String msg);

    protected void contact(String message) {
        mediator.contact(message, this);
    }
}
