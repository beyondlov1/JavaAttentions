package com.beyond.demo.playground.spring.jms.mdp1;

import com.beyond.demo.playground.spring.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component("springSender1")
public class SpringSender {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Destination destination;

    public void send(){
        Person person = new Person();
        person.setName("hahah");
        person.setAge(23);
        jmsTemplate.convertAndSend(destination,person);
    }
}
