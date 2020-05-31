package com.beyond.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionUtils {

	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSession getSession() throws IOException {
		SqlSession session = null;
		if (sqlSessionFactory == null) {
			String resource = "mybatis-config.xml";
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			sqlSessionFactory = builder.build(is);
		}
		session = sqlSessionFactory.openSession();
		return session;
	}

}
