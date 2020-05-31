package com.beyond.beandefinitionregisterdemos.controller;

import com.beyond.beandefinitionregisterdemos.CustomRegisterTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    RegisteredBean obj1Bean;
    @Autowired
    RegisteredBean obj2Bean;

    @RequestMapping("/registeredBean1")
    public Object registeredBean1(){
        return obj1Bean.getName();
    }
    @RequestMapping("/registeredBean2")
    public Object registeredBean2(){
        return obj2Bean.getName();
    }


    @Autowired
    CustomRegisterTarget target;

    @RequestMapping("/customRegisterTarget")
    public Object customRegisterTarget(){
        return target.showMe();
    }
}
