package com.hai.desinpattern.action.command.demo2;

public class MyCommand implements Command {
    private Receiver receiver;


    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.action();
    }
}
