package com.hai.javase.gui.imsg.message;

/**
 * Created by Administrator on 2017/9/23.
 */
public class FriendMessage extends Message {
    public FriendMessage() {
        this.setType(Message.MESSAGE_TYPE_SERVER_FRIEND);
    }

    @Override
    protected void send() {
        System.out.println(this.getClass().getName()+".send()...");
    }
}
