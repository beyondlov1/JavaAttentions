package com.beyond.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JDBCUtils {

	private static ThreadLocal<Connection> tl = new ThreadLocal<>();// 只能放在外边,每一个tl对象对应一个map,
																	// 即使在统一线程,不同对象取出的值也不一样

	private static HikariDataSource ds = null;

	static {
		System.out.println("HikariDatasourse");// 首次执行JDBCUtils中的方法时调用此静态块
		// mysql
		Properties pro = new Properties();
		InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("Hikari-config.properties");
		try {
			pro.load(resourceAsStream);
			HikariConfig config = new HikariConfig(pro);
			ds = new HikariDataSource(config);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 配置HikariDataSource

		// oracle
		// Properties props = new Properties();
		// props.setProperty("dataSourceClassName",
		// "oracle.jdbc.pool.OracleDataSource");
		// props.setProperty("dataSource.url",
		// "jdbc:oracle:thin:@localhost:1521:xe");
		// props.setProperty("dataSource.user", "scott");
		// props.setProperty("dataSource.password", "beyondlov1");
		//
		// HikariConfig config = new HikariConfig(props);
		// HikariDataSource ds = new HikariDataSource(config);
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = tl.get();
			if (conn == null) {
				conn = ds.getConnection();
				tl.set(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 开启事务
	public static void startTransaction() {
		Connection conn = JDBCUtils.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	// 回滚
	public static void rollback() {
		Connection conn = JDBCUtils.getConnection();
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	// 释放资源
	public static void release(Connection conn, Statement sm, ResultSet rs) {

		if (conn != null) {
			try {
				conn.close();
				tl.remove();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (sm != null) {
			try {
				sm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void release(Connection conn, Statement sm) {

		if (conn != null) {
			try {
				conn.close();
				tl.remove();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (sm != null) {
			try {
				sm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void release(Connection conn) {

		if (conn != null) {
			try {

				conn.close();
				tl.remove();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void release() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				// conn.commit();
				conn.close();
				tl.remove();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
