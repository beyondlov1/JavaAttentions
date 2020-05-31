package demo8;

import org.hibernate.Session;
import org.hibernate.Transaction;

import demo3.utils.HibernateUtils;

/*
 * 事务: 原子性, 一致性, 隔离性, 持久性
 * hibernate 隔离性: <property name="hibernate.connection.isolation">2</property>  (1,2,4,8)
 */
public class demo8 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		ts.commit();
	}

}
