package com.beyond.demo.playground;

public class ToLoad {
    static {
        System.out.println("i am loaded");
    }

    public ToLoad(){
        System.out.println("i am created");
    }
}
