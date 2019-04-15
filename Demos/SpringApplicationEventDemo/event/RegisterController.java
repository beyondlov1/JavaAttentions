package com.example.demo.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author beyondlov1
 * @date 2019/04/15
 */
@RestController
public class RegisterController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping
    public Object register(){
        applicationEventPublisher.publishEvent(new RegisterEvent("beyond"));
        return "ok";
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
