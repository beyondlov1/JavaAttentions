package com.beyond.demo.mapper;

import com.beyond.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> selectAllUser();
    List<User> selectAllUser(RowBounds rowBounds);
    List<User> selectByUsername(@Param("username") String username);
    List<User> selectByUser(User user);
//    void insertUser(User user);
    void insertUser(Map user);
}
