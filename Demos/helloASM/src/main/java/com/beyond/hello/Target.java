package com.beyond.hello;

@Single
public class Target {
    private String name;

    public String getName(){
        return name;
    }

    @Single
    public void move(){
        System.out.println("I am moving");
    }
}
