package com.beyond.demo.playground.spring.jms.template;

import com.beyond.demo.playground.spring.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.*;
import java.io.IOException;

public class JmsConfiguration {

    @Bean
    public Destination destination(){
        return new ActiveMQQueue("testQueue");
    }

    @Bean
    public MessageConverter getConverter(){
        return new MessageConverter() {
            @Override
            public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = null;
                try {
                     jsonString = mapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(o);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                return session.createTextMessage(jsonString);
            }

            @Override
            public Object fromMessage(Message message) throws JMSException, MessageConversionException {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    return  mapper.readValue(((TextMessage)message).getText(), Person.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

}
