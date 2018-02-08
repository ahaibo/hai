package com.hai.ijavase.desinpattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */
public class Controller {
    private Command openTv;
    private Command changeChannelTv;
    private Command closeTv;

    private List<Command> commands = new ArrayList<>();

    public Controller() {
    }

    public Controller(Command openTv, Command changeChannelTv, Command closeTv) {
        this.openTv = openTv;
        this.changeChannelTv = changeChannelTv;
        this.closeTv = closeTv;
    }

    public void open() {
        openTv.execute();
    }

    public void changeChannel() {
        changeChannelTv.execute();
    }

    public void close() {
        closeTv.execute();
    }

    public List<Command> addCommand(Command command) {
        this.commands.add(command);
        return this.commands;
    }

    public void executes() {
        for (Command command : commands) {
            command.execute();
        }
        this.commands.clear();
    }

    public Command getOpenTv() {
        return openTv;
    }

    public void setOpenTv(Command openTv) {
        this.openTv = openTv;
    }

    public Command getChangeChannelTv() {
        return changeChannelTv;
    }

    public void setChangeChannelTv(Command changeChannelTv) {
        this.changeChannelTv = changeChannelTv;
    }

    public Command getCloseTv() {
        return closeTv;
    }

    public void setCloseTv(Command closeTv) {
        this.closeTv = closeTv;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
