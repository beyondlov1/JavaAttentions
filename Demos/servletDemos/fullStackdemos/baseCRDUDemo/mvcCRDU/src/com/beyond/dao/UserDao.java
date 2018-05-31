package com.beyond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beyond.entity.User;
import com.beyond.utils.JDBCUtils;

public class UserDao {

	public void addUser(User userinfo) throws SQLException {
		String id = userinfo.getId();
		String username = userinfo.getUsername();
		String password = userinfo.getPassword();

		String sql = "insert into userinfo (id, username, password ) values( ?,?,?)";

		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, username);
		ps.setString(3, password);

		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public void deleteUserById(String id) throws SQLException {

		String sql = "delete from userinfo where id=?";
		System.out.println(id);
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public void updateUserById(String id, User userinfo) throws SQLException {

		String username = userinfo.getUsername();
		String password = userinfo.getPassword();
		System.out.println(password);

		String sql = "update userinfo set username=?, password=? where id=?";

		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, id);

		ps.executeUpdate();

		ps.close();
		conn.close();
	}

	public User selectById(String id) throws SQLException {

		System.out.println(id);
		User userinfo = null;
		String username = null;
		String password = null;

		String sql = "select * from userinfo where id = ?";

		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			userinfo = new User();
			username = rs.getString("username");
			password = rs.getString("password");
			userinfo.setId(id);
			userinfo.setUsername(username);
			userinfo.setPassword(password);

		}

		ps.close();
		conn.close();
		return userinfo;
	}

	public List<User> selectAll() throws SQLException {

		List<User> list = new ArrayList<>();

		String sql = "select id,username, password from userinfo";

		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String id = rs.getString(1);
			String username = rs.getString(2);
			String password = rs.getString(3);
			User userinfo = new User();
			userinfo.setId(id);
			userinfo.setUsername(username);
			userinfo.setPassword(password);
			list.add(userinfo);
		}

		ps.close();
		conn.close();
		return list;
	}

}
