package com.hai.javase.gui.imsg.message;

/**
 * Created by Administrator on 2017/9/23.
 */
public class ClientChartMessage extends Message {

    public ClientChartMessage() {
        this.setType(Message.MESSAGE_TYPE_CLIENT_MSG);
    }

    @Override
    protected void send() {
        System.out.println(this.getClass().getName()+".send()...");
    }
}
