package com.beyond.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.beyond.entity.Customer;
import com.beyond.utils.HibernateUtils;

public class Dao {

	@SuppressWarnings("unchecked")
	public List<Customer> selectAll() {
		EntityManager em = HibernateUtils.getEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("from Customer");
		List<Customer> list = query.getResultList();

		em.getTransaction().commit();
		em.close();

		return list;
	}
}
