/**
 *
 */
package com.hai.desinpattern.create.singleton;

import java.io.ObjectStreamException;

/**
 * 单例模式：双重检查模式
 *
 * @author hai
 */
public class Singleton {

    private static volatile Singleton instane;

    private Singleton() {
        //防止反射破解单例
        if (null != instane) {
            throw new RuntimeException();
        }
    }

    /**
     * 防止反序列化破解单例模式
     * 反序列化时：如果定义了readResolve方法，则直接返回instance。不再单独创建对象
     *
     * @return
     */
    private Object readResolve() throws ObjectStreamException {
        return instane;
    }

    public static Singleton getInstance() {
        if (null == instane) {
            synchronized (Singleton.class) {
                if (null == instane) {
                    instane = new Singleton();
                }
            }
        }
        return instane;
    }

    public void doSomething() {
        System.out.println(this.getClass().getName() + ".doSomething()...");
    }
}
