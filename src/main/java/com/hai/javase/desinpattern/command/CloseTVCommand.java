package com.hai.javase.desinpattern.command;

/**
 * Created by Administrator on 2018/2/8.
 */
public class CloseTVCommand implements Command {

    private Television television;

    public CloseTVCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        this.television.close();
    }
}
