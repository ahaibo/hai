package com.hai.desinpattern.action.command.demo2;

public class HelloReceiver implements Receiver {
    @Override
    public void action() {
        System.out.println("hello action...");
    }
}
