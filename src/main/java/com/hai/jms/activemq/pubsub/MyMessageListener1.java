package com.hai.jms.activemq.pubsub;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener1 implements MessageListener {
    static Logger log = Logger.getLogger(MyMessageListener1.class);

    @Override
    public void onMessage(Message message) {
        try {
            log.info("subscriber1 received activemq msg: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}