/**
 *
 */
package com.hai.jms.activemq.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

/**
 * ActiveMQ 点对点链接 生产方
 *
 * @author as
 */
public class Consumer {

    private final static String NAME = ActiveMQConnection.DEFAULT_USER;
    private final static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private final static String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private final static String SUBJECT = "FirstQueue1";
    static Logger log = Logger.getLogger(Consumer.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        ConnectionFactory factory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
        try {
            factory = new ActiveMQConnectionFactory(NAME, PASSWORD, BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(SUBJECT);
            consumer = session.createConsumer(destination);
            // 设置消息监听
            consumer.setMessageListener(new MyMessageListener());
            // 不推荐的处理模式
            // consumerWithNormal(consumer);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            /*if (null != consumer) {
                try {
					consumer.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			if (null != session) {
				try {
					session.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			if (null != connection) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}*/
        }
    }

    /**
     * @param consumer
     * @throws JMSException
     */
    private static void consumerWithNormal(MessageConsumer consumer) throws JMSException {
        TextMessage message;
        while (true) {
            message = (TextMessage) consumer.receive(100000);
            if (null != message) {
                log.info("consumer received activemq msg: " + message.getText());
            } else {
                break;
            }
        }
    }

    static class MyMessageListener implements MessageListener {
        static Logger log = Logger.getLogger(MyMessageListener.class);

        @Override
        public void onMessage(Message message) {
            try {
                log.info("consumer received activemq msg: " + ((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
