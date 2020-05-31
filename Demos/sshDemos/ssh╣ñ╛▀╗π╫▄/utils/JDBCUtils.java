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

	private static ThreadLocal<Connection> tl = new ThreadLocal<>();// ֻ�ܷ������,ÿһ��tl�����Ӧһ��map,
																	// ��ʹ��ͳһ�߳�,��ͬ����ȡ����ֵҲ��һ��

	private static HikariDataSource ds = null;

	static {
		System.out.println("HikariDatasourse");// �״�ִ��JDBCUtils�еķ���ʱ���ô˾�̬��
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

		// ����HikariDataSource

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

	// ��������
	public static void startTransaction() {
		Connection conn = JDBCUtils.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	// �ع�
	public static void rollback() {
		Connection conn = JDBCUtils.getConnection();
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	// �ͷ���Դ
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
