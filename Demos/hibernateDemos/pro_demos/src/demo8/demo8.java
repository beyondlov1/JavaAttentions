package demo8;

import org.hibernate.Session;
import org.hibernate.Transaction;

import demo3.utils.HibernateUtils;

/*
 * ����: ԭ����, һ����, ������, �־���
 * hibernate ������: <property name="hibernate.connection.isolation">2</property>  (1,2,4,8)
 */
public class demo8 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		ts.commit();
	}

}
