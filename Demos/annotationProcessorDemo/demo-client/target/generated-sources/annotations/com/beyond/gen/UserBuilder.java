package com.beyond.gen;

import com.beyond.pojo.User;
import java.lang.String;

public final class UserBuilder {
  private String username;

  private String password;

  private int age;

  public UserBuilder username(String username) {
    this.username = username;return this;
  }

  public UserBuilder password(String password) {
    this.password = password;return this;
  }

  public UserBuilder age(int age) {
    this.age = age;return this;
  }

  public User build() {
    User instance = new User();
    instance.setUsername(this.username);
    instance.setPassword(this.password);
    instance.setAge(this.age);
    return instance;
  }
}
