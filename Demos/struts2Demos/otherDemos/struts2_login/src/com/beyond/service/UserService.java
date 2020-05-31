package com.beyond.service;

import java.sql.SQLException;

import com.beyond.dao.Dao;
import com.beyond.entity.Userinfo;

public class UserService {
	public Userinfo login(Userinfo user) throws SQLException {
		Dao dao = new Dao();
		Userinfo sqlUser = dao.selectUserByUser(user);
		return sqlUser;
	}

	public int signup(Userinfo user) throws SQLException {
		Dao dao = new Dao();
		return dao.addUser(user);
	}

	public Userinfo findByUsername(Userinfo user) throws SQLException {
		Dao dao = new Dao();
		return dao.selectUserByName(user);
	}

}
