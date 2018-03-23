/**
 *
 */
package com.hai.javase.desinpattern.adapter;

/**
 * 源角色实现类(适配者)
 *
 * @author Administrator
 */
public class Adaptee implements IAdaptee {

    @Override
    public void doSomething() {
        System.out.println("doSomething execution in class: ".concat(Adaptee.class.getName()));
    }
}
