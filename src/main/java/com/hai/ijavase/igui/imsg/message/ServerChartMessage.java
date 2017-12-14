package com.hai.ijavase.igui.imsg.message;

/**
 * Created by Administrator on 2017/9/23.
 */
public class ServerChartMessage extends Message {

    public ServerChartMessage() {
        this.setType(Message.MESSAGE_TYPE_SERVER_MSG);
    }

    @Override
    protected void send() {
        System.out.println(this.getClass().getName() + ".send()...");
    }
}
