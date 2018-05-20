package demo7;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import demo3.utils.HibernateUtils;
import demo7.entity.Customer;

/*
 * 批量检索
 */
public class demo7 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		/*
		 * 一方 在set加
		 */
		// 有多少有多少order就创建多少个查询语句+1. 不用批量,3条查询语句(因为有缓存)
		List<Customer> list = session.createQuery("from Customer").list();
		for (Customer customer1 : list) {
			System.out.println(customer1.getAddress() + "----" + customer1.getOrders().size());
		}

		System.out.println("-----------------------------");

		// <set name="orders" cascade="all" inverse="true" batch-size="10">
		// 两条查询语句, 一般 batch-size 设置为 10 或者 20
		List<Customer> list2 = session.createQuery("from Customer").list();
		for (Customer customer1 : list2) {
			System.out.println(customer1.getAddress() + "----" + customer1.getOrders().size());
		}

		/*
		 * 多方 在一方加
		 */
		// <class name="Customer" table="CUSTOMER_7" batch-size="10">
		List<Customer> list3 = session.createQuery("from Customer").list();
		for (Customer customer1 : list3) {
			System.out.println(customer1.getAddress() + "----" + customer1.getOrders().size());
		}

		ts.commit();
	}

}
