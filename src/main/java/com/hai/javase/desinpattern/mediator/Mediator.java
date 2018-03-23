package com.hai.javase.desinpattern.mediator;

/**
 * 中介者模式：抽象中介者(以租房为例)
 * 中介者模式（Mediator Pattern）是用来降低多个对象和类之间的通信复杂性。
 * 这种模式提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护。中介者模式属于行为型模式。
 * Created by Administrator on 2018/2/8.
 */
public abstract class Mediator {
    //声明一个联络方法
    abstract void contact(String message, Person person);
}
