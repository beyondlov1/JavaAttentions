package demo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo1.entity.Userinfo;

public class demo1 {
	public static void main(String[] args) {
		Configuration config = new Configuration().configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction ts = session.beginTransaction();
		Userinfo user = new Userinfo();
		user.setUsername("jack");
		user.setPassword("jpassword");
		session.save(user);
		// Userinfo userinfo = (Userinfo) session.get(Userinfo.class, "");
		ts.commit();
	}
}
