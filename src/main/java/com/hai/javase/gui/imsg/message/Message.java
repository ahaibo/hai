package com.hai.javase.gui.imsg.message;

import com.hai.javase.gui.imsg.utils.CommonUtils;

/**
 * 通信消息信息
 * Created by Administrator on 2017/9/23.
 */
public abstract class Message {

    //客户端发送聊天内容
    public static final byte MESSAGE_TYPE_CLIENT_MSG = 0;
    //服务端发送消息给客户端
    public static final byte MESSAGE_TYPE_SERVER_MSG = 1;
    //服务端刷新好友列表
    public static final byte MESSAGE_TYPE_SERVER_FRIEND = 2;

    private byte type;
    private int length;
    private byte[] content;

    public Message() {
    }

    public Message(byte type, int length, byte[] content) {
        this.type = type;
        this.length = length;
        this.content = content;
    }

    /**
     * 打包消息信息
     * int length = 1 字节类型 + 4 字节消息长度 + content.length 消息字节;
     * byte[] bytes = new byte[length]
     *
     * @return
     */
    public byte[] packMessage() {
        //字节数组长度为 1 字节类型 + 4 字节消息长度 + content.length 消息字节
        byte[] bytes = new byte[1 + 4 + content.length];

        //消息长度
        bytes[0] = type;

        // 1 -4 字节存储消息长度
        byte[] lenthBytes = CommonUtils.inttoBytes(length);
        System.arraycopy(lenthBytes, 0, bytes, 1, 4);

        // 5 - content.length 存储消息字节
        System.arraycopy(content, 0, bytes, 5, content.length);

        return bytes;
    }

    protected abstract void send();

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public boolean isClientMsg() {
        return this.type == MESSAGE_TYPE_CLIENT_MSG;
    }

    public boolean isServerMsg() {
        return this.type == MESSAGE_TYPE_SERVER_MSG;
    }

    public boolean isFriendMsg() {
        return this.type == MESSAGE_TYPE_SERVER_FRIEND;
    }

    public void setTypeToClientMsg() {
        this.type = MESSAGE_TYPE_CLIENT_MSG;
    }

    public void setTypeToServerMsg() {
        this.type = MESSAGE_TYPE_SERVER_MSG;
    }

    public void setTypeToFriendMsg() {
        this.type = MESSAGE_TYPE_SERVER_FRIEND;
    }
}
