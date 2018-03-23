/**
 *
 */
package com.hai.javase.desinpattern.observer;

import com.hai.javase.desinpattern.observer.impl.ConcreteObservable;
import com.hai.javase.desinpattern.observer.impl.ConcreteObserver;

import java.util.Observer;

/**
 * @author Administrator
 */
public class Client {

    public static void main(String[] args) {
        // 创建具体被观察者
        ConcreteObservable observable = new ConcreteObservable();

        // 创建具体观察者
        Observer observer = new ConcreteObserver();

        // 被观察者添加观察者【观察者观察被观察者】
        observable.addObserver(observer);

        // 被观察者开始活动
        observable.doSomething();
    }
}
