package demo2;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import demo1.entity.Userinfo;
import demo2.utils.HibernateUtils;

public class demo2 {
	public static void main(String[] args) {

		// 增
		add();

		// 查一个
		String id = "402881f16373f76a016373f76e0d0000";
		// Userinfo user = queryById2(id);
		// System.out.println(user.getId());

		// 改
		// Userinfo user = new Userinfo();
		// user.setId(id);
		// user.setUsername("joe");
		// update(user);

		// 删
		// deleteById(id);

		// 查询所有
		// queryAll();

		queryAll3();

	}

	public static void add() {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		Userinfo user = new Userinfo();
		user.setUsername("王五");
		user.setPassword("jpassword");
		session.save(user);

		ts.commit();
	}

	public static void deleteById(String id) {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		Userinfo userinfo = session.get(Userinfo.class, id);
		session.delete(userinfo);

		ts.commit();
	}

	public static void update(Userinfo userinfo) {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		session.update(userinfo);

		ts.commit();
	}

	public static Userinfo queryById1(String id) {
		Userinfo user = null;
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		user = session.get(Userinfo.class, id);

		ts.commit();
		return user;
	}

	public static Userinfo queryById2(String id) {
		Userinfo user = null;
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		user = session.load(Userinfo.class, id);
		System.out.println(user.getUsername());

		ts.commit();
		return user;
	}

	public static void queryAll() {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		Query<Userinfo> query = session.createQuery("from Userinfo");
		List<Userinfo> list = query.getResultList();
		for (Userinfo user : list) {
			System.out.println(user.getId());
		}

		ts.commit();
	}

	public static void queryAll2() {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Userinfo> query = cb.createQuery(Userinfo.class);
		Root<Userinfo> userinfo = query.from(Userinfo.class);
		query.select(userinfo).where(cb.equal(userinfo.get("name"), "jack"));
		Query<Userinfo> query2 = session.createQuery(query);
		List<Userinfo> resultList = query2.getResultList();
		for (Userinfo user : resultList) {
			System.out.println(user.getId());
		}

		ts.commit();
	}

	public static void queryAll3() {
		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		Query<Userinfo> query = session.createNativeQuery("select * from userinfo", Userinfo.class);
		query.setFirstResult(1);
		query.setMaxResults(5);

		List<Userinfo> list = query.getResultList();
		for (Userinfo user : list) {
			System.out.println(user.getUsername());
		}

		ts.commit();
	}
}
