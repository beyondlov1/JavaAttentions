package com.beyond.service.impl;

import java.util.List;

import com.beyond.dao.GeneralDao;
import com.beyond.dao.Impl.GeneralDaoImpl;
import com.beyond.service.Service;

public class ServiceImpl<T> implements Service<T> {
	private GeneralDao<T> dao = new GeneralDaoImpl<T>();

	public void add(T t) {
		dao.add(t);
	}

	public void remove(T t) {
		dao.delete(t);
	}

	public void modify(T t) {
		dao.update(t);
	}

	public T find(Class<T> c, String id) {
		return dao.selectById(c, id);
	}

	public List<T> findAll(Class<T> c) {
		return dao.selectAll(c);
	}

}
