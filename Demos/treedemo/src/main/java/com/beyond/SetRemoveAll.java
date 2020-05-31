package com.beyond;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetRemoveAll {
    public static void main(String[] args) {
        List<User> list = new ArrayList<User>();
        list.add(new User("1","2"));
        list.add(new User("3","2"));
        list.add(new User("4","2"));
        list.add(new User("5","2"));
        list.add(new User("6","2"));
        list.add(new User("7","2"));


        Set<User> userSet = new HashSet<User>(list);
        userSet.removeAll(new ArrayList<User>());
        System.out.println(userSet);
    }
}
