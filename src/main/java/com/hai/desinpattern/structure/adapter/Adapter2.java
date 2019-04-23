/**
 *
 */
package com.hai.desinpattern.structure.adapter;

/**
 * 组合适配器,持有视频对象,一般采用此种方式，继承不易扩展
 *
 * @author Administrator
 */
public class Adapter2 implements Target {

    private IAdaptee adaptee;

    public Adapter2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        System.out.println("request execution in class: ".concat(Adapter2.class.getName()));
        adaptee.doSomething();
    }

}
