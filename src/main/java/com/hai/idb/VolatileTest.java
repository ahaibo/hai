/**
 *
 */
package com.hai.idb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author as
 */
public class VolatileTest {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                    }
                    Obj.getInstance();
                }
            }).start();
        }
        System.out.println("Obj.list.size: " + Obj.list.size());
    }
}

class Obj {
    private volatile static Obj instance;
    public static List<Obj> list = new ArrayList<>();

    private Obj() {
    }

    public static Obj getInstance() {
        if (null == instance) {
            synchronized (Obj.class) {
                if (null == instance) {
                    instance = new Obj();
                    list.add(instance);
                }
            }
        }
        return instance;
    }
}
