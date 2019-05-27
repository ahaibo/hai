package com.hai.desinpattern.action.memento;

/**
 * Created by Administrator on 2018/2/9.
 */
public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}
/*
模式的优缺点
优点
1、 给用户提供了一种可以恢复状态的机制。可以使用户能够比较方便地回到某个历史的状态。
2、 实现了信息的封装。使得用户不需要关心状态的保存细节。

缺点
  消耗资源。如果类的成员变量过多，势必会占用比较大的资源，而且每一次保存都会消耗一定的内存。

模式适用场景
1、 需要保存一个对象在某一个时刻的状态或部分状态。
2、 如果用一个接口来让其他对象得到这些状态，将会暴露对象的实现细节并破坏对象的封装性，一个对象不希望外界直接访问其内部状态，通过负责人可以间接访问其内部状态。

模式总结
1、 备忘录模式可以实现在不破坏封装的前提下，捕获一个类的内部状态，并且在该对象之外保存该对象的状态，保证该对象能够恢复到历史的某个状态。
2、 备忘录模式实现了内部状态的封装，除了创建它的原发器之外其他对象都不能够访问它。
3、 备忘录模式会占用较多的内存，消耗资源。
 */