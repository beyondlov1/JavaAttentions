package com.beyond.multidatasourcedemos.service;

import com.beyond.multidatasourcedemos.datasource.DataSource;
import com.beyond.multidatasourcedemos.datasource.DataSourceType;
import com.beyond.multidatasourcedemos.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @DataSource(type = DataSourceType.MySQL)
    public Object findAll() {
        return userMapper.selectAll();
    }
    @DataSource(type = DataSourceType.H2)
    public Object findAll2() {
        return userMapper.selectAllH2();
    }
}
