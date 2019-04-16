package com.hai.javase.desinpattern.bridge.computer;

import com.hai.javase.desinpattern.bridge.computer.band.sub.Dell;
import com.hai.javase.desinpattern.bridge.computer.band.sub.Lenovo;
import com.hai.javase.desinpattern.bridge.computer.band.sub.ShenZhou;
import com.hai.javase.desinpattern.bridge.computer.computer.Computer;
import com.hai.javase.desinpattern.bridge.computer.computer.sub.Desktop;
import com.hai.javase.desinpattern.bridge.computer.computer.sub.Laptop;
import com.hai.javase.desinpattern.bridge.computer.computer.sub.Pad;

/**
 * 桥接模式：主要用于解决多重继承的场景
 * 多重继承违背了单一性原则，复用性较差，类的个数也非常多。桥接模式可以极大的减少类的个数，从而降低管理和维护的成本。
 * <p>
 * 桥接模式极大的提高了系统的可扩展性，在几个变化维度中任意的扩展一个维度，不不需要修改原有的的系统，符合开闭原则。
 */
public class Client {
    public static void main(String[] args) {
        Computer computer = new Desktop(new Lenovo());
        computer.sale();

        computer = new Laptop(new ShenZhou());
        computer.sale();

        computer = new Pad(new Dell());
        computer.sale();
    }
}
