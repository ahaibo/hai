package com.hai.desinpattern.action.command.demo2;

public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action() {
        this.command.execute();
    }
}
