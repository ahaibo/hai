/**
 *
 */
package com.hai.jms.activemq.pubsub;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

/**
 * @author as
 */
public class Publisher {

    private final static String NAME = ActiveMQConnection.DEFAULT_USER;
    private final static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private final static String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private final static String SUBJECT = "FirstTopic1";
    static Logger log = Logger.getLogger(Publisher.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        ConnectionFactory factory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        try {
            factory = new ActiveMQConnectionFactory("cptuatactivemqrw", "cptuatactivemqrw", "ssl://activemquat.cptuat.net:61617");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(SUBJECT);
            producer = session.createProducer(destination);

            sendMessage(session, producer);

            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (null != producer) {
                try {
                    producer.close();
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
            }
        }
    }

    private static void sendMessage(Session session, MessageProducer producer) throws JMSException {
        Message message;
        for (int i = 0; i < 10; i++) {
            String msg = "activemq publisher " + System.currentTimeMillis() + " - " + i;
            message = session.createTextMessage(msg);
            producer.send(message);
            log.info("produce " + msg);
        }
    }

}
