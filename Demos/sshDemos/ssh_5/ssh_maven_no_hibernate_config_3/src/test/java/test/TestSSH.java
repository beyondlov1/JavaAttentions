package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.beyond.dao.BaseDao;
import com.beyond.dao.impl.BookDaoImpl;
import com.beyond.entity.Book;
import com.beyond.entity.User;
import com.beyond.service.impl.BookServiceImpl;
import com.beyond.service.impl.UserServiceImpl;

public class TestSSH {

	@Test
	private void testPage() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");
		BookDaoImpl dao = (BookDaoImpl) context.getBean("BookDaoImp");
		User user = new User();
		user.setUsername("beyond");
		User found = userServiceImpl.find(user);
		dao.count(found);
	}

	@Test
	public void testBatch() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");

		List<User> findAll = userServiceImpl.findAllFetch(User.class);
		System.out.println(findAll.size());
		for (User user : findAll) {
			System.out.println(user.getUsername());
			List<Book> books = user.getBooks();
			for (Book book : books) {
				System.out.println(book.getName());
			}
		}
	}

	@Test
	public void testBookService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookServiceImpl bookServiceImpl = (BookServiceImpl) context.getBean("bookServiceImpl");
		UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");
		Book book = new Book();
		User owner = userServiceImpl.find(User.class, "402881eb63c37cf30163c37cfa510000");

		// User owner = new User();
		// owner.setUsername("gulong1");
		// owner.setPassword("gulong1");
		book.setName("luxiaofeng");

		book.setOwner(owner);
		owner.getBooks().add(book);
		userServiceImpl.save(owner);
	}

	@Test
	public void testUserService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Object obj = context.getBean("userService");
		UserServiceImpl userServiceImpl = (UserServiceImpl) obj;
		List<User> findAll = userServiceImpl.findAll(User.class);
		System.out.println(findAll);
	}

	@Test
	public void testBaseDao() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BaseDao<User> baseDao = (BaseDao<User>) context.getBean("baseDao");
		List<User> selectAll = baseDao.selectAll(User.class);
		System.out.println(selectAll.size());

	}

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

		/*
		 * Session session = hibernateTemplate.getSessionFactory().openSession(); User
		 * user2 = session.get(User.class, "4028acfa63bac91b0163bac99e930000");
		 * System.out.println(user2.getId()); User user3 = session.get(User.class,
		 * "4028acfa63bac91b0163bac99e930000"); System.out.println(user3.getId()); User
		 * user4 = session.get(User.class, "4028acfa63bac91b0163bac99e930000");
		 * System.out.println(user4.getId()); System.out.println(session.hashCode());
		 * session.close();
		 * 
		 * Session session1 = hibernateTemplate.getSessionFactory().openSession(); User
		 * user5 = session1.get(User.class, "4028acfa63bac91b0163bac99e930000");
		 * System.out.println(user5.getId()); System.out.println(session1.hashCode());
		 * session1.close();
		 */
	}
}
