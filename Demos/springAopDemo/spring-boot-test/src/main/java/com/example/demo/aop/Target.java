package com.example.demo.aop;

import org.springframework.stereotype.Component;

/**
 * @author beyondlov1
 * @date 2019/04/11
 */
@Component
public class Target {
    @TimeTrace
    public Object execute(){
        System.out.println("business");
        return "business result";
    }
}
