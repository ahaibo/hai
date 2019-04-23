package com.hai.desinpattern.action.command;

/**
 * Created by Administrator on 2018/2/8.
 */
public class OpenTVCommand implements Command {

    private Television television;

    public OpenTVCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        this.television.open();
    }
}
