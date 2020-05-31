package com.beyond.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyond.dao.BaseDao;
import com.beyond.service.BaseService;

@Service
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	@Override
	public void save(T t) {
		baseDao.add(t);
	}

	@Override
	public void modify(T t) {
		baseDao.update(t);
	}

	@Override
	public void remove(T t) {
		baseDao.delete(t);
	}

	@Override
	public T find(Class<T> c, String id) {
		return baseDao.selectById(c, id);
	}

	@Override
	public T find(T t) {
		return baseDao.selectByExample(t);
	}

	@Override
	public Boolean isExist(T t) {
		if (baseDao.selectByExample(t) == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<T> findAll(Class<T> c) {
		return baseDao.selectAll(c);
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

}
