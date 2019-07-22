package com.beyond.demo.mapper;

import com.beyond.demo.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAllUser();
    List<User> selectByUsername(@Param("username") String username);
    List<User> selectByUser(User user);
}
