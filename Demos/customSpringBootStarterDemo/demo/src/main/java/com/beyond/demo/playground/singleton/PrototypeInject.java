package com.beyond.demo.playground.singleton;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrototypeInject {
    boolean paramConsume() default false;
}
