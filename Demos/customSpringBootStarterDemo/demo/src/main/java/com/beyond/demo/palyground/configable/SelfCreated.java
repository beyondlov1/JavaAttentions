package com.beyond.demo.palyground.configable;

import com.beyond.demo.controller.DemoController;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(value = "selfCreated",autowire = Autowire.BY_NAME)
public class SelfCreated implements SelfCreatedInterface {

    private String name;

    private DemoController controller;

    @Override
    public void play() {
        System.out.println(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setController(DemoController controller) {
        this.controller = controller;
    }
}
