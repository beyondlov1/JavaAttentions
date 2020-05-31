package com.beyond.beandefinitionregisterdemos;

@CustomAnnotation2
public class CustomRegisterTarget {
    public Object showMe(){
        System.out.println("i am registered");
        return "i am alive";
    }
}
