package com.beyond.protobuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadDemo {
    public static void main(String[] args) throws IOException {
        try (FileInputStream inputStream = new FileInputStream("D:\\document\\GitHub\\JavaAttentions\\Demos\\protobufDemos\\protobuf\\src\\main\\resources\\user.obj")) {
            UserDemo.User user = UserDemo.User.parseFrom(inputStream);
            System.out.println(user);
        }

    }
}
