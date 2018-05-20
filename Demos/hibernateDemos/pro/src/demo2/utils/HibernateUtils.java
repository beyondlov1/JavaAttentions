package demo2.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory factory;

	static {
		Configuration config = new Configuration().configure();
		factory = config.buildSessionFactory();
	}

	public static Session getSession() {
		return factory.getCurrentSession();
	}
}
