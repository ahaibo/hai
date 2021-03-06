/**
 *
 */
package com.hai.desinpattern.structure.adapter;

/**
 * 类适配器【继承源角色实现类并实现目标接口】
 *
 * @author Administrator
 */
public class Adapter extends Adaptee implements Target {

    @Override
    public void request() {
        System.out.println("request execution in class: ".concat(Adapter.class.getName()));
        super.doSomething();
        System.out.println();
    }

}
