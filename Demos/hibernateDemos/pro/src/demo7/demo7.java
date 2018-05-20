package demo7;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import demo3.utils.HibernateUtils;
import demo7.entity.Customer;

/*
 * ��������
 */
public class demo7 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		/*
		 * һ�� ��set��
		 */
		// �ж����ж���order�ʹ������ٸ���ѯ���+1. ��������,3����ѯ���(��Ϊ�л���)
		List<Customer> list = session.createQuery("from Customer").list();
		for (Customer customer1 : list) {
			System.out.println(customer1.getAddress() + "----" + customer1.getOrders().size());
		}

		System.out.println("-----------------------------");

		// <set name="orders" cascade="all" inverse="true" batch-size="10">
		// ������ѯ���, һ�� batch-size ����Ϊ 10 ���� 20
		List<Customer> list2 = session.createQuery("from Customer").list();
		for (Customer customer1 : list2) {
			System.out.println(customer1.getAddress() + "----" + customer1.getOrders().size());
		}

		/*
		 * �෽ ��һ����
		 */
		// <class name="Customer" table="CUSTOMER_7" batch-size="10">
		List<Customer> list3 = session.createQuery("from Customer").list();
		for (Customer customer1 : list3) {
			System.out.println(customer1.getAddress() + "----" + customer1.getOrders().size());
		}

		ts.commit();
	}

}
