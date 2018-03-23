/**
 *
 */
package com.hai.javase.desinpattern.observer.impl;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 *
 * @author Administrator
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("观察者：有动静、开始 update 操作了 --》 " + arg);
    }

}
