package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.beyond.entity.User;

public class TestSSH {

	// test hibernate -- success!
	@Test
	public void test1() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		System.out.println(session);
		User user = new User();
		user.setUsername("wangwu");
		user.setPassword("password");
		session.save(user);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}

	@Test
	public void test2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Object bean = context.getBean("userAction");
		System.out.println(bean);
	}

	@Test
	public void test2ndcache() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HibernateTemplate hibernateTemplate = (HibernateTemplate) context.getBean("hibernateTemplate");
		User user = hibernateTemplate.load(User.class, "4028acfa63bac91b0163bac99e930000");
		System.out.println(user.getId());
		hibernateTemplate.clear();
		User user6 = hibernateTemplate.load(User.class, "4028acfa63bac91b0163bac99e930000");
		System.out.println(user6.getId());
		User user7 = hibernateTemplate.load(User.class, "4028acfa63bac3580163bac467710000");
		System.out.println(user7.getId());

		Session session = hibernateTemplate.getSessionFactory().openSession();
		User user2 = session.get(User.class, "4028acfa63bac91b0163bac99e930000");
		System.out.println(user2.getId());
		User user3 = session.get(User.class, "4028acfa63bac91b0163bac99e930000");
		System.out.println(user3.getId());
		User user4 = session.get(User.class, "4028acfa63bac91b0163bac99e930000");
		System.out.println(user4.getId());
		System.out.println(session.hashCode());
		session.clear();

		User user5 = session.get(User.class, "4028acfa63bac91b0163bac99e930000");
		System.out.println(user5.getId());
		System.out.println(session.hashCode());
		session.close();

		long secondLevelCacheHitCount = hibernateTemplate.getSessionFactory().getStatistics()
				.getSecondLevelCacheHitCount();
		long getSecondLevelCacheMissCount = hibernateTemplate.getSessionFactory().getStatistics()
				.getSecondLevelCacheMissCount();
		long getQueryCacheHitCount = hibernateTemplate.getSessionFactory().getStatistics().getQueryCacheHitCount();
		long getSecondLevelCachePutCount = hibernateTemplate.getSessionFactory().getStatistics()
				.getSecondLevelCachePutCount();

		System.out.println(secondLevelCacheHitCount);
		System.out.println(getSecondLevelCacheMissCount);
		System.out.println(getQueryCacheHitCount);
	}
}
