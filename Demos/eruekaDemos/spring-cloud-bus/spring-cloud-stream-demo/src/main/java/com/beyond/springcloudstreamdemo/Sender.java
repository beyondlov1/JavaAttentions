package com.beyond.springcloudstreamdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(Source.class)
public class Sender {

    @Autowired
    MessageChannel output;

    public void send(){
        output.send(new GenericMessage<>("send"));
    }
}
