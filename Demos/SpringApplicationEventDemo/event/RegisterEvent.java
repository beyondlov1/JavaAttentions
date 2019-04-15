package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author beyondlov1
 * @date 2019/04/15
 */
public class RegisterEvent extends ApplicationEvent {
    /**

     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public RegisterEvent(Object source) {
        super(source);
    }

}
