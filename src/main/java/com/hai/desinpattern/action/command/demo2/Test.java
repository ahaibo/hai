package com.hai.desinpattern.action.command.demo2;

public class Test {
    public static void main(String[] args) {
        Receiver receiver = new HelloReceiver();
        Command command = new MyCommand(receiver);
        Invoker invoker = new Invoker(command);
        invoker.action();
    }
}
