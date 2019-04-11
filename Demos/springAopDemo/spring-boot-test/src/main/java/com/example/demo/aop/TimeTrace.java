package com.example.demo.aop;


import java.lang.annotation.*;

/**
 * @author beyondlov1
 * @date 2019/04/11
 */
@java.lang.annotation.Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeTrace {

}
