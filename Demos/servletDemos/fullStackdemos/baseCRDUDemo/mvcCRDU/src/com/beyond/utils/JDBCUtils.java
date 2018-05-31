package com.beyond.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {
	public static Connection getConnection() {
		String driverName = "oracle.jdbc.pool.OracleDataSource";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "scott";
		String password = "beyondlov1";
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
