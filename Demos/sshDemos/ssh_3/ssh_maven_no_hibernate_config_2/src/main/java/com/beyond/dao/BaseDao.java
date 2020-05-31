package com.beyond.dao;

import java.util.List;

public interface BaseDao<T> {

	void add(T t);

	void delete(T t);

	void update(T t);

	T selectById(Class<T> c, String id);

	T selectByExample(T t);

	List<T> selectAll(Class<T> c);
}