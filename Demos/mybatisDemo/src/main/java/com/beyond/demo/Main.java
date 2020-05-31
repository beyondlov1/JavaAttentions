package com.beyond.demo;

import com.beyond.demo.bean.SexEnum;
import com.beyond.demo.bean.User;
import com.beyond.demo.mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
//        SqlSession session = SessionUtil.getSession();
//        UserMapper mapper = session.getMapper(UserMapper.class);
//        List<User> users = mapper.selectByUsername("beyond");
//        for (User user : users) {
//            System.out.println(user.getUsername());
//        }
//        session.close();

        SqlSession session = SessionUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User queryObject = new User();
        queryObject.setUsername("beyond");
        RowBounds rowBounds = new RowBounds(3,2);
        List<User> users = mapper.selectAllUser(rowBounds);
        for (User user : users) {
            System.out.println(user.getUsername());
            System.out.println(user.getSex());
        }
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("hello");
        user.setPassword("world");
        user.setSex(SexEnum.MALE);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        map.put("sex",user.getSex());
        mapper.insertUser(map);
        session.commit();
        session.close();
    }
}
