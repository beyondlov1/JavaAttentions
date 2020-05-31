package com.beyond.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.beyond.entity.Customer;
import com.beyond.entity.Order;
import com.beyond.utils.HibernateUtils;

public class HibernateTest {

	private EntityManager em;
	private EntityTransaction ts;

	@Before
	public void before() {
		em = HibernateUtils.getEntityManager();
		ts = em.getTransaction();
		ts.begin();
	}

	@Test
	public void persist() {

		Customer customer = new Customer();
		customer.setName("jack");

		Order order1 = new Order();
		order1.setPrice("9898");
		order1.setCustomer(customer);

		Order order2 = new Order();
		order2.setPrice("766776");
		order2.setCustomer(customer);

		customer.getOrders().add(order2);
		customer.getOrders().add(order1);

		em.persist(customer);
	}

	@Test
	public void merge() {

		Customer customer = em.find(Customer.class, "4028aa3d638b21ed01638b21f1f20000");
		customer.setName("joy");
		em.merge(customer);
	}

	@Test
	public void remove() {

		Customer customer = em.find(Customer.class, "4028aa3d638b21ed01638b21f1f20000");

		em.remove(customer);
	}

	@Test
	public void query() {

		Customer c = em.find(Customer.class, "4028aa3d638b21ed01638b21f1f20000");
		System.out.println(c.getName());
	}

	@Test
	public void queryAll() {

		Query query = em.createQuery("from Customer");
		List<Customer> list = query.getResultList();
		for (Customer c : list) {
			System.out.println(c.getId() + ": " + c.getName());
		}

		em.clear();

		Customer c1 = em.find(Customer.class, "4028aa3d638b28c201638b28c7ec0000");
		System.out.println(c1.getName());

	}

	@Test
	public void queryFirstResult() {

		Query query = em.createQuery("from Customer");
		query.setFirstResult(1);
		query.setMaxResults(1);
		List<Customer> list = query.getResultList();
		for (Customer c : list) {
			System.out.println(c.getId() + ": " + c.getName());
		}
	}

	@After
	public void after() {
		ts.commit();
		em.close();
	}
}
