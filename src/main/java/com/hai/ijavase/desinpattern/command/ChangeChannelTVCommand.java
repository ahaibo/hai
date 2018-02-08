package com.hai.ijavase.desinpattern.command;

/**
 * Created by Administrator on 2018/2/8.
 */
public class ChangeChannelTVCommand implements Command {

    private Television television;

    public ChangeChannelTVCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        this.television.changeChannel();
    }
}
