package com.beyond.demo.playground.spring.jms.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component("templateReceiver")
public class SpringReceiver {
    @Autowired
    JmsTemplate jmsTemplate;

    public void onMessage(Message message) {
        Object o = jmsTemplate.receiveAndConvert();  // 阻塞
    }
}
