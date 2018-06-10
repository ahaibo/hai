package com.hai.javase.io.nio.netty.customprotocol;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class XLRequest extends XLObject {
    private int command;// 结果码

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}