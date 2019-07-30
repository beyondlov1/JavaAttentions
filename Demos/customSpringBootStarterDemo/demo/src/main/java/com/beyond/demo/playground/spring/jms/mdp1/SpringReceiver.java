package com.beyond.demo.playground.spring.jms;

import com.beyond.demo.playground.spring.Person;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class SpringReceiver implements MessageListener {

//    @JmsListener(destination = "testQueue", containerFactory = "myFactory")
    public void receive(Person person){
        System.out.println(person);
//        Object o = jmsTemplate.receiveAndConvert(destination);
//        return o.toString();
    }


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
