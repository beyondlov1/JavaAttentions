package com.beyond.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.beyond.dao.BaseDao;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(T t) {
		hibernateTemplate.saveOrUpdate(t);
	}

	public void delete(T t) {
		hibernateTemplate.delete(t);
	}

	public void update(T t) {
		hibernateTemplate.update(t);
	}

	public T selectById(Class<T> c, String id) {
		return hibernateTemplate.load(c, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> selectAll(Class<T> c) {
		DetachedCriteria criteria = DetachedCriteria.forClass(c);
		List<T> findByCriteria = (List<T>) hibernateTemplate.findByCriteria(criteria);
		return findByCriteria;
	}

	@Override
	public T selectByExample(T t) {

		List<T> list = hibernateTemplate.findByExample(t);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
