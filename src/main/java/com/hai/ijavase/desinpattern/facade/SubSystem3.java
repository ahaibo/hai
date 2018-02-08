package com.hai.ijavase.desinpattern.facade;

/**
 * Created by Administrator on 2018/2/5.
 */
public class SubSystem3 {
    public void on() {
        System.out.println(this.getClass().getName() + ".on...");
    }

    public void off() {
        System.out.println(this.getClass().getName() + ".off...");
    }
}
