package com.beyond.chat.controller;

import org.springframework.stereotype.Component;

@Component
public class HelloMessageListener {
    public void handleMessage(String message){
        System.out.println(message);
    }
}
