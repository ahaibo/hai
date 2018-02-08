package com.hai.ijavase.desinpattern.facade;

/**
 * Created by Administrator on 2018/2/5.
 */
public class Facade {
    private SubSystem1 subSystem1;
    private SubSystem2 subSystem2;
    private SubSystem3 subSystem3;

    public Facade() {
    }

    public Facade(SubSystem1 subSystem1, SubSystem2 subSystem2, SubSystem3 subSystem3) {
        this.subSystem1 = subSystem1;
        this.subSystem2 = subSystem2;
        this.subSystem3 = subSystem3;
    }

    public void on() {
        this.subSystem1.on();
        this.subSystem2.on();
        this.subSystem3.on();
    }

    public void off() {
        this.subSystem1.off();
        this.subSystem2.off();
        this.subSystem3.off();
    }

    public SubSystem1 getSubSystem1() {
        return subSystem1;
    }

    public void setSubSystem1(SubSystem1 subSystem1) {
        this.subSystem1 = subSystem1;
    }

    public SubSystem2 getSubSystem2() {
        return subSystem2;
    }

    public void setSubSystem2(SubSystem2 subSystem2) {
        this.subSystem2 = subSystem2;
    }

    public SubSystem3 getSubSystem3() {
        return subSystem3;
    }

    public void setSubSystem3(SubSystem3 subSystem3) {
        this.subSystem3 = subSystem3;
    }
}
