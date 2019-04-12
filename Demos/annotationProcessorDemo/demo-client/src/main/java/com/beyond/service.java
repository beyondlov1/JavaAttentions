package com.beyond;

import com.beyond.gen.UserBuilder;
import com.beyond.pojo.User;

/**
 * @author beyondlov1
 * @date 2019/04/12
 */
public class service {
    public static void main(String[] args) {
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.age(10)
                .username("beyond")
                .password("password")
                .build();
        System.out.println(user.toString());
    }
}
