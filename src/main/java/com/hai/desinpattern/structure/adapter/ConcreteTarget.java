/**
 *
 */
package com.hai.desinpattern.structure.adapter;

/**
 * @author Administrator
 */
public class ConcreteTarget implements Target {

    @Override
    public void request() {
        System.out.println("request execution in " + ConcreteTarget.class.getName());
        System.out.println();
    }

}
