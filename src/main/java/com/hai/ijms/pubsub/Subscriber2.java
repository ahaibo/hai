/**
 * 
 */
package com.hai.ijms.pubsub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

/**
 * ActiveMQ 点对点链接 生产方
 * 
 * @author as
 *
 */
public class Subscriber2 {

	private final static String NAME = ActiveMQConnection.DEFAULT_USER;
	private final static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private final static String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private final static String SUBJECT = "FirstTopic1";
	static Logger log = Logger.getLogger(Subscriber2.class);

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
			destination = session.createTopic(SUBJECT);
			consumer = session.createConsumer(destination);
			// 这次消息监听
			consumer.setMessageListener(new MyMessageListener2());
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
		}
	}

}
