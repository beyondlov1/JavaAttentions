package com.beyond.dao;

import java.util.List;

public interface BaseDao<T> {

	void add(T t);

	void delete(T t);

	void update(T t);

	T select();

	List<T> selectAll();

	// void selectByPage(Page page);

}
