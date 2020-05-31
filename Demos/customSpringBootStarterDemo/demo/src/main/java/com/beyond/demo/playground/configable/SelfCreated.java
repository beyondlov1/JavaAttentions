package com.beyond.demo.playground.configable;

import com.beyond.demo.controller.DemoController;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(value = "selfCreated", autowire = Autowire.BY_TYPE)
public class SelfCreated implements SelfCreatedInterface {

    private String name;

    @Autowired
    private DemoController demoController;

    public void setDemoController(DemoController demoController) {
        this.demoController = demoController;
    }

    @Override
    public void play() {
        System.out.println(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public DemoController getDemoController() {
        return demoController;
    }
}
