/**
 *
 */
package com.hai.jms.activemq.pubsub;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;


/**
 * ActiveMQ 点对点链接 生产方
 *
 * @author as
 */
public class Subscriber1 {

    private final static String NAME = ActiveMQConnection.DEFAULT_USER;
    private final static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private final static String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private final static String SUBJECT = "FirstTopic1";
    static Logger log = Logger.getLogger(Subscriber1.class);

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
            factory = new ActiveMQConnectionFactory("cptuatactivemqrw", "cptuatactivemqrw", "ssl://activemquat.cptuat.net:61617");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(SUBJECT);
            consumer = session.createConsumer(destination);
            // 这次消息监听
            consumer.setMessageListener(new MyMessageListener1());
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            System.out.println(1);
        }
    }

}
