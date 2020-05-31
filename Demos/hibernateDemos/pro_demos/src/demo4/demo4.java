package demo4;

import org.hibernate.Session;
import org.hibernate.Transaction;

import demo3.utils.HibernateUtils;
import demo4.entity.Customer;
import demo4.entity.Order;

/*
 * 级联操作 一对多
 */
public class demo4 {
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		Customer customer = new Customer();
		customer.setAddress("address1");

		Order order = new Order();
		Order order1 = new Order();
		order.setPrice(1000.0);
		order1.setPrice(10000.0);

		// 关联
		customer.getOrders().add(order1);
		customer.getOrders().add(order);

		order.setCustomer(customer);
		order1.setCustomer(customer);

		// 保存
		session.save(customer);
		// session.save(order);
		// session.save(order1);

		ts.commit();
	}

}
