package com.beyond.thrift;

import org.apache.thrift.TException;

public class HelloMyService implements MyService.Iface {

    public User getUser() throws TException {
        System.out.println("service: getUser is invoked");
        User user = new User();
        user.setName("username");
        user.setAge((short)10);
        return user;
    }
}
