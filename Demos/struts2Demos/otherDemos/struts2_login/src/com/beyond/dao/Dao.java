package com.beyond.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.beyond.entity.Userinfo;
import com.beyond.utils.JDBCUtils;

public class Dao {

	private QueryRunner qr = new QueryRunner();

	public Userinfo selectUserByUser(Userinfo user) throws SQLException {
		String username = user.getUsername();
		String password = user.getPassword();
		String sql = "select * from userinfo where username=? and password=?";

		Connection conn = JDBCUtils.getConnection();
		return qr.query(conn, sql, new BeanHandler<Userinfo>(Userinfo.class), username, password);
	}

	public int addUser(Userinfo user) throws SQLException {
		String sql = "insert into userinfo values(?,?,?)";
		Connection conn = JDBCUtils.getConnection();
		return qr.update(conn, sql, user.getId(), user.getUsername(), user.getPassword());
	}

	public Userinfo selectUserByName(Userinfo user) throws SQLException {
		String username = user.getUsername();
		String sql = "select * from userinfo where username=?";
		Connection conn = JDBCUtils.getConnection();
		return qr.query(conn, sql, new BeanHandler<Userinfo>(Userinfo.class), username);
	}
}
