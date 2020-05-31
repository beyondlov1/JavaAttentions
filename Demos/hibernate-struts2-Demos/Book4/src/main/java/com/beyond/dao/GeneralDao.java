package com.beyond.dao;

import java.util.List;

public interface GeneralDao<T> {

	public void add(T t);

	public void delete(T t);

	public void update(T t);

	public T selectById(Class<T> c, String id);

	public List<T> selectAll(Class<T> c);
}
