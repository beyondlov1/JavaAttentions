package com.example.demo.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author beyondlov1
 * @date 2019/03/29
 */
@RestController
public class ControllerDemo {
    @Autowired
    private ServiceDemo serviceDemo;

    @Value("${demo.name}")
    private String name;

    @CrossOrigin
    @RequestMapping("/testPropertyString")
    public Object testPropertyString(){
        return serviceDemo.getPropertyDemo().getName();
    }

    @RequestMapping("/testPropertyList")
    public Object testPropertyList(){
        return serviceDemo.getPropertyDemo().getList();
    }

    @RequestMapping("/testValue")
    public Object testValue(){
        return name;
    }
}
