package com.beyond.protobuf;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteDemo {
    public static void main(String[] args) throws IOException {
        UserDemo.User user = UserDemo.User.newBuilder()
                .setName("username1")
                .setAge(23)
                .build();
        try (FileOutputStream output = new FileOutputStream("D:\\document\\GitHub\\JavaAttentions\\Demos\\protobufDemos\\protobuf\\src\\main\\resources\\user.obj")) {
            user.writeTo(output);
        }

    }
}
