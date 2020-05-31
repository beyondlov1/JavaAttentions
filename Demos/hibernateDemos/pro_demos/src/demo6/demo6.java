package demo6;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import demo3.utils.HibernateUtils;
import demo6.entity.Customer;

/*
 * 内连接 迫切内连接(针对查询多条数据)
 * 左连接 迫切左连接(针对查询多条数据)
 * 命名查询
 */
public class demo6 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		// 内连接 (一对多) 默认lazy="true", 用的时候才加载, 查询三次
		Query<Customer> query1 = session.createQuery("select c from Customer c, Order o where c.id=o.customer.id");
		List<Customer> list1 = query1.list();
		for (Customer customer : list1) {
			System.out.println(customer.getOrders());
		}

		// 内迫切连接 只能为lazy="false", 查询一次
		Query<Customer> query2 = session.createQuery("from Customer c inner join fetch c.orders");
		List<Customer> list2 = query2.list();
		for (Customer customer : list2) {
			System.out.println(customer.getOrders());
		}

		System.out.println("-------------------------------------");

		// 外连接 (一对多) 默认lazy="true", 用的时候才加载, 查询两次. 第一次只查询一方数据
		Query<Customer> query3 = session
				.createQuery("select c from Customer c left join Order o on c.id=o.customer.id");
		List<Customer> list3 = query3.list();
		for (Customer customer : list3) {// 第一次查询出两个订单后有缓存 , 所以只需查询少一次
			System.out.println(customer.getOrders());
		}

		// 迫切左连接 只能为lazy="false", 查询一次, 直接将多方塞入一方
		Query<Customer> query4 = session.createQuery("from Customer c left outer join fetch c.orders");
		List<Customer> list4 = query4.list();
		for (Customer c : list4) {
			System.out.println(c.getOrders());
		}

		System.out.println("-------------------------------------");

		// 命名查询
		Query<Customer> query5 = session.createNamedQuery("findByHql");
		List<Customer> list5 = query5.list();
		for (Customer c : list5) {
			System.out.println(c.getOrders());
		}
		Query<Customer> query6 = session.createNamedQuery("findBySql");
		List<Customer> list6 = query6.list();
		for (Customer c : list6) {
			System.out.println(c.getOrders());
		}

		ts.commit();
	}

}
