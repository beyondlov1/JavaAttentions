package com.beyond.demo.playground.spring.jms.plain;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue testQueue = session.createQueue("testQueue");
        MessageConsumer consumer = session.createConsumer(testQueue);

        connection.start();
        while (true){
            Message message = consumer.receive();
            String hello = message.getStringProperty("hello");
            System.out.println(hello);
        }

    }
}
