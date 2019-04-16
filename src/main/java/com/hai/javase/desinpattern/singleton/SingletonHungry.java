package com.hai.javase.desinpattern.singleton;

/**
 * 单例模式：饥汉模式
 */
public class SingletonHungry {
    private static final SingletonHungry instance = new SingletonHungry();


    private SingletonHungry() {
        if (null != instance) {
            throw new RuntimeException();
        }
    }

    public static SingletonHungry getInstance() {
        return instance;
    }
}
