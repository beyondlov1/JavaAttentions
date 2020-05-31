package demo3;

import java.util.List;

import javax.persistence.FlushModeType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import demo3.entity.Userinfo;
import demo3.utils.HibernateUtils;

/*
 * ��֤Flush
 */
public class demo3 {
	public static void main(String[] args) {

		// queryAll();
		// testCache2();
		testFlush();
	}

	public static void testFlush() {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		session.setFlushMode(FlushModeType.COMMIT);
		Userinfo user = new Userinfo(); // ˲ʱ״̬

		user = session.get(Userinfo.class, "402881f16378a5be016378a5c2100000");

		System.out.println(user.getUsername());
		user.setUsername("jack1123uuuu3");

		Userinfo userinfo = session.get(Userinfo.class, "402881f16378a5be016378a5c2100000");

		System.out.println(userinfo.getUsername());
		ts.commit();
	}

	public static void queryAll() {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();
		Query<Userinfo> query = session.createQuery("from Userinfo");
		List<Userinfo> list = query.getResultList();
		for (Userinfo user : list) {
			System.out.println(user.getId());
		}

	}

	public static void testCache() {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		Userinfo user = new Userinfo(); // ˲ʱ״̬
		user.setUsername("joy");
		user.setPassword("joyPassword");

		session.save(user);// �־�״̬

		// session.clear();

		ts.commit();
	}

	public static void testCache1() {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		Userinfo user = new Userinfo(); // ˲ʱ״̬
		user.setUsername("joy");
		user.setPassword("joyPassword");

		Userinfo userinfo = session.get(Userinfo.class, "402881f16378a5be016378a5c2100000");// �־�״̬

		session.clear();

		System.out.println(userinfo.getUsername()); // ˲ʱ״̬

		ts.commit();
	}

	public static void testCache2() {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		Userinfo user = new Userinfo(); // ˲ʱ״̬

		user = session.get(Userinfo.class, "402881f16378a5be016378a5c2100000");

		System.out.println(user.getUsername());
		user.setUsername("jack11233");

		// session.update(user);// �־�״̬

		Userinfo userinfo = session.get(Userinfo.class, "402881f16378a5be016378a5c2100000");

		System.out.println(userinfo.getUsername());
		ts.commit();
	}
}
