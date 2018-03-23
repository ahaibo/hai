package com.hai.javase.desinpattern.flyweight.simple;

import com.hai.javase.desinpattern.flyweight.Flyweight;
import com.hai.javase.desinpattern.flyweight.FlyweightFactory;

//客户端类
//单纯享元模式：在单纯享元模式中，所有的享元对象都是可以共享的，即所有抽象享元类的子类都可共享，不存在非共享具体享元类。
public class Client {

    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory(new Character('a'));
        fly.operation("First Call");

        fly = factory.factory(new Character('b'));
        fly.operation("Second Call");

        fly = factory.factory(new Character('a'));
        fly.operation("Third Call");

        System.out.println("factory.getContainer().size: " + factory.getContainer().size());
    }

}