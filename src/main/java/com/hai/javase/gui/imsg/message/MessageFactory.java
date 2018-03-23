package com.hai.javase.gui.imsg.message;

import com.hai.javase.gui.imsg.utils.CommonUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/9/23.
 */
public class MessageFactory {

    public static Message createMessage(InputStream is) throws IOException {
        Message message = null;
        int type = CommonUtils.readMessageType(is);
        switch (type) {
            case Message.MESSAGE_TYPE_CLIENT_MSG:
                message = new ClientChartMessage();
                break;
            case Message.MESSAGE_TYPE_SERVER_MSG:
                message = new ServerChartMessage();
                break;
            case Message.MESSAGE_TYPE_SERVER_FRIEND:
                message = new FriendMessage();
                break;
        }

        if (null != message) {
            message.setLength(CommonUtils.readMessageLength(is));
            message.setContent(CommonUtils.readMessageContent(is, message.getLength()));
        }

        return message;
    }
}
