package com.beyond.service;

import java.util.List;

public interface BaseService<T> {

	void save(T t);

	void modify(T t);

	void remove(T t);

	T find(Class<T> c, String id);

	T find(T t);

	Boolean isExist(T t);

	List<T> findAll(Class<T> c);
}
