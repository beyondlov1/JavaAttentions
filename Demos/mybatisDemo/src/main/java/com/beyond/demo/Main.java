package com.beyond.demo;

import com.beyond.demo.bean.User;
import com.beyond.demo.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.selectAllUser();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        session.close();
    }
}
