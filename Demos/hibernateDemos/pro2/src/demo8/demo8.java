package demo8;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import demo8.entity.Customer;

/*
 * 
 */
public class demo8 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myhibernate");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// Customer customer = new Customer();
		// customer.setAddress("address2");
		//
		// Order order = new Order();
		// Order order1 = new Order();
		// order.setPrice(2000.0);
		// order1.setPrice(20000.0);
		//
		// System.out.println("wangc");
		// // 保存
		// em.merge(customer);
		// // session.save(order);
		// // session.save(order1);
		// em.getTransaction().commit();
		// em.close();

		Query query = em.createQuery("from Customer");
		List<Customer> list = query.getResultList();
		for (Customer customer1 : list) {
			System.out.println(customer1.getId());
		}

		// 放到一级缓存，二级缓存
		Customer c = (Customer) em.find(Customer.class, "402881ee6387be60016387be64a20000");// 会发出sql
		System.out.println(c);

		Customer c1 = (Customer) em.find(Customer.class, "402881ee6387be60016387be64a20000");// 使用的是一级缓存
		// 不发sql
		System.out.println(c1); // c与c1是同一个对象，因为地址一样

		em.getTransaction().commit();// 自动关闭session

	}

}
