package com.beyond.springcloudstreamdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    Sender sender;

    @RequestMapping("/send")
    public Object send(){
        sender.send();
        return "ok";
    }
}
