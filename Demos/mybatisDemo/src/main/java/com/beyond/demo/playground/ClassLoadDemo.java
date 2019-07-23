package com.beyond.demo.playground;

import java.lang.reflect.Method;

public class ClassLoadDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<ToLoad> toLoadClass = (Class<ToLoad>) Class.forName(ToLoad.class.getName());
    }
}
