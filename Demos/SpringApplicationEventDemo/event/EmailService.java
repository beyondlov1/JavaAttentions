package com.example.demo.event;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 继承和注解用一个就行，否则执行两次
 * @author beyondlov1
 * @date 2019/04/15
 */
@Component
public class EmailService implements ApplicationListener<RegisterEvent> {

    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationEvent(RegisterEvent event) {
        System.out.println("EmailService : receive name - "+event.getSource());
    }

}
