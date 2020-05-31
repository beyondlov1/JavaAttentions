package com.beyond.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SessionUtil {
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSession() throws IOException {
        SqlSession session = null;
        if (sqlSessionFactory == null) {
            String resource = "mybatis-config.xml";
            // resources 调用相当于classLoader调用, 使用的是classpath的根目录开始
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        session = sqlSessionFactory.openSession();
        return session;
    }
}
