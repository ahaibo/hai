package com.hai.desinpattern.create.singleton;

/**
 * 单例模式：静态内部类模式
 */
public class SingletonInnerStaticClass {
    private SingletonInnerStaticClass() {
    }

    private static class SingletonInnerStaticClassInstanceClass {
        private static final SingletonInnerStaticClass instance = new SingletonInnerStaticClass();
    }

    public static SingletonInnerStaticClass getInstance() {
        return SingletonInnerStaticClassInstanceClass.instance;
    }
}
