package com.beyond.demo.playground.spring.jms.mdp1;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component("springReceiver1")
public class SpringReceiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("onmessage");
        try {
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
