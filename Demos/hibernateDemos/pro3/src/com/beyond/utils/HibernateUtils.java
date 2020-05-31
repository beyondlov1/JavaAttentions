package com.beyond.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtils {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static ThreadLocal<EntityManager> tl = new ThreadLocal<EntityManager>();

	public static EntityManager getEntityManager() {
		emf = Persistence.createEntityManagerFactory("myhibernate");

		em = emf.createEntityManager();

		return em;
	}
}
