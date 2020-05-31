package com.beyond.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author admin
 *
 */
public class HibernateUtils {
	private static EntityManagerFactory factory;
	private static EntityManager em;
	private static ThreadLocal<EntityManager> tl = new ThreadLocal<EntityManager>();

	static {
		factory = Persistence.createEntityManagerFactory("myhibernate");
	}

	public static EntityManager getEntityManager() {
		EntityManager em = tl.get();
		if (em == null) {
			em = factory.createEntityManager();
			tl.set(em);
		}
		return em;
	}

	public static void release() {
		em.close();
		factory.close();
	}
}
