package demo6;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import demo3.utils.HibernateUtils;
import demo6.entity.Customer;

/*
 * ������ ����������(��Բ�ѯ��������)
 * ������ ����������(��Բ�ѯ��������)
 * ������ѯ
 */
public class demo6 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		// ������ (һ�Զ�) Ĭ��lazy="true", �õ�ʱ��ż���, ��ѯ����
		Query<Customer> query1 = session.createQuery("select c from Customer c, Order o where c.id=o.customer.id");
		List<Customer> list1 = query1.list();
		for (Customer customer : list1) {
			System.out.println(customer.getOrders());
		}

		// ���������� ֻ��Ϊlazy="false", ��ѯһ��
		Query<Customer> query2 = session.createQuery("from Customer c inner join fetch c.orders");
		List<Customer> list2 = query2.list();
		for (Customer customer : list2) {
			System.out.println(customer.getOrders());
		}

		System.out.println("-------------------------------------");

		// ������ (һ�Զ�) Ĭ��lazy="true", �õ�ʱ��ż���, ��ѯ����. ��һ��ֻ��ѯһ������
		Query<Customer> query3 = session
				.createQuery("select c from Customer c left join Order o on c.id=o.customer.id");
		List<Customer> list3 = query3.list();
		for (Customer customer : list3) {// ��һ�β�ѯ�������������л��� , ����ֻ���ѯ��һ��
			System.out.println(customer.getOrders());
		}

		// ���������� ֻ��Ϊlazy="false", ��ѯһ��, ֱ�ӽ��෽����һ��
		Query<Customer> query4 = session.createQuery("from Customer c left outer join fetch c.orders");
		List<Customer> list4 = query4.list();
		for (Customer c : list4) {
			System.out.println(c.getOrders());
		}

		System.out.println("-------------------------------------");

		// ������ѯ
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
