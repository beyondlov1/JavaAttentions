package com.beyond.demo.playground.spring.jms.mdp2;

import com.beyond.demo.playground.spring.Person;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SpringReceiver{

    @JmsListener(destination = "testQueue", containerFactory = "myFactory")
    public void receive(Person person){
        System.out.println(person);
    }
}
