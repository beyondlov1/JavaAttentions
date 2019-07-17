package com.beyond.demo.mapper;

import com.beyond.demo.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> selectAllUser();
}
