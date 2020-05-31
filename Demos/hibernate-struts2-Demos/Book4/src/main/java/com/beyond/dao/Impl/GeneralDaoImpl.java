package com.beyond.dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.beyond.dao.GeneralDao;
import com.beyond.utils.HibernateUtils;

public class GeneralDaoImpl<T> implements GeneralDao<T> {

	private EntityManager em = HibernateUtils.getEntityManager();

	@Override
	public void add(T t) {
		em.persist(t);
	}

	@Override
	public void delete(T t) {
		em.remove(t);
	}

	@Override
	public void update(T t) {
		em.merge(t);
	}

	@Override
	public T selectById(Class<T> c, String id) {
		return em.find(c, id);
	}

	@Override
	public List<T> selectAll(Class<T> c) {
		return em.createQuery("from " + c.getSimpleName()).getResultList();
	}

}
