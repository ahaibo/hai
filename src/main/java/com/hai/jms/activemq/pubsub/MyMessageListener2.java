package com.hai.jms.activemq.pubsub;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener2 implements MessageListener {
    static Logger log = Logger.getLogger(MyMessageListener2.class);

    @Override
    public void onMessage(Message message) {
        try {
            log.info("subscriber2 received activemq msg: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}