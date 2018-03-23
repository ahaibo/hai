/**
 *
 */
package com.hai.javase.desinpattern.adapter;

/**
 * @author Administrator
 */
public class Client {
    public static void main(String[] args) {
        Target local = new ConcreteTarget();
        local.request();

        Target remote = new Adapter();
        remote.request();
    }
}
