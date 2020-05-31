package com.beyond.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.beyond.utils.JDBCUtils;

public class BaseDaoImpl implements com.beyond.dao.BaseDao {

	@Override
	public <T> int addBean(T t) {

		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		// 创建sql
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(t.getClass().getSimpleName().toLowerCase() + " (");

		Field[] fields = t.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			if (i == fields.length - 1) {
				sql.append(field.getName());
			} else {
				sql.append(field.getName() + ",");
			}

		}

		sql.append(") values(");

		for (int i = 0; i < fields.length; i++) {

			if (i == fields.length - 1) {
				sql.append("?");
			} else {
				sql.append("?,");
			}
		}
		sql.append(")");

		// 执行sql
		Object[] objs = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			Object object = null;
			try {
				object = field.get(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objs[i] = object;
		}

		int update = 0;
		try {
			update = qr.update(conn, sql.toString(), objs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public <T> int deleteBean(Class<T> c, String key, Object value) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		// 创建sql
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append(c.getSimpleName().toLowerCase());
		sql.append(" where ");
		sql.append(key);
		sql.append("=?");

		// 执行sql
		int update = 0;
		try {
			update = qr.update(conn, sql.toString(), value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public <T> int updateBean(String key, Object value, T t) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		// 创建sql
		StringBuilder sql = new StringBuilder();
		sql.append("update ");
		sql.append(t.getClass().getSimpleName().toLowerCase() + " set ");

		Field[] fields = t.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			if (i == fields.length - 1) {
				sql.append(field.getName() + "=?");
			} else {
				sql.append(field.getName() + "=?,");
			}

		}

		sql.append(" where ");
		sql.append(key);
		sql.append("=?");

		// 执行sql
		Object[] objs = new Object[fields.length + 1];
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			Object object = null;
			try {
				object = field.get(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objs[i] = object;
		}
		objs[fields.length] = value;

		int update = 0;
		try {
			update = qr.update(conn, sql.toString(), objs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public <T> List<T> selectAllBean(Class<T> c) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		// 创建sql
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			if (i == fields.length - 1) {
				sql.append(field.getName());
			} else {
				sql.append(field.getName() + ", ");
			}
		}

		sql.append(" from ");
		sql.append(c.getSimpleName().toLowerCase());

		// 执行sql
		List<T> list = null;
		try {
			list = qr.query(conn, sql.toString(), new BeanListHandler<T>(c));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public <T> T selectBean(Class<T> c, String key, Object value) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		// 创建sql
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			if (i == fields.length - 1) {
				sql.append(field.getName());
			} else {
				sql.append(field.getName() + ", ");
			}
		}

		sql.append(" from ");
		sql.append(c.getSimpleName().toLowerCase());

		sql.append(" where ");
		sql.append(key);
		sql.append("=?");

		// 执行sql
		T t = null;
		try {
			t = qr.query(conn, sql.toString(), new BeanHandler<T>(c), value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public <T> int deleteBean(Class<T> c, String id) {
		// TODO Auto-generated method stub
		return deleteBean(c, "id", id);
	}

	@Override
	public <T> int updateBean(String id, T t) {
		// TODO Auto-generated method stub
		return updateBean("id", id, t);
	}

	public <T> int updateBean(T t) {
		// 获取id
		Object id = null;
		try {
			Field field = t.getClass().getDeclaredField("id");
			field.setAccessible(true);
			id = field.get(t);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return updateBean("id", id, t);
	}

	@Override
	public <T> T selectBean(Class<T> c, String id) {
		return selectBean(c, "id", id);
	}

	@Override
	public <T> List<T> selectAllBean(Class<T> c, int startIndex, int offset) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		// 创建sql
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			if (i == fields.length - 1) {
				sql.append(field.getName());
			} else {
				sql.append(field.getName() + ", ");
			}
		}

		sql.append(" from ");
		sql.append(c.getSimpleName().toLowerCase());
		sql.append(" limit ?,?");// MySQL

		// 执行sql
		List<T> list = null;
		try {
			list = qr.query(conn, sql.toString(), new BeanListHandler<T>(c), startIndex, offset);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public <T> int selectTotalCount(Class<T> c) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		Long count = 0L;
		try {
			count = (Long) qr.query(conn, "select count(*) from " + c.getSimpleName().toLowerCase(),
					new ScalarHandler());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return count.intValue();
	}

	@Override
	public <T> int selectCountByCondition(Class<T> c, String condition) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		Long count = 0L;
		try {
			count = (Long) qr.query(conn,
					"select count(*) from " + c.getSimpleName().toLowerCase() + " where " + condition,
					new ScalarHandler());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return count.intValue();
	}

	@Override
	public <T> List<T> selectAllBean(Class<T> c, int startIndex, int offset, String orderColumn, String orderMethod) {

		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		// 创建sql
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			if (i == fields.length - 1) {
				sql.append(field.getName());
			} else {
				sql.append(field.getName() + ", ");
			}
		}

		sql.append(" from ");
		sql.append(c.getSimpleName().toLowerCase());
		sql.append(" order by ");
		sql.append(orderColumn);
		sql.append(" ");
		sql.append(orderMethod);
		sql.append(" limit ?,?");// MySQL

		// 执行sql
		List<T> list = null;
		try {
			list = qr.query(conn, sql.toString(), new BeanListHandler<T>(c), startIndex, offset);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public <T> List<T> selectAllBean(Class<T> c, int startIndex, int offset, String orderColumn) {
		return selectAllBean(c, startIndex, offset, orderColumn, "asc");
	}

	@Override
	public <T> int updateBean(Class<T> c, String whereKey, String whereValue, Map<String, Object> map) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();

		// 创建sql
		StringBuilder sql = new StringBuilder();
		sql.append("update ");
		sql.append(c.getSimpleName().toLowerCase() + " set ");

		Iterator<String> keyIt = map.keySet().iterator();
		while (keyIt.hasNext()) {
			String key = keyIt.next();
			sql.append(key);
			sql.append("=?");
			if (keyIt.hasNext()) {
				sql.append(",");
			}
		}

		sql.append(" where ");
		sql.append(whereKey);
		sql.append("=?");

		// 执行sql
		Object[] objs = new Object[map.size() + 1];
		Iterator<Map.Entry<String, Object>> entryIt = map.entrySet().iterator();
		int i = 0;
		while (entryIt.hasNext()) {
			Entry<String, Object> entry = entryIt.next();
			Object value = entry.getValue();
			objs[i] = value;
			i++;
		}
		objs[map.size()] = whereValue;

		int update = 0;
		try {
			update = qr.update(conn, sql.toString(), objs); // MySQL
															// 默认timestamp自动更新为更新时间
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

	public <T> int updateBean(Class<T> c, String id, Map<String, Object> map) {
		return updateBean(c, "id", id, map);
	}
}
