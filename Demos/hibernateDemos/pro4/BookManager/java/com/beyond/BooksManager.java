package com.beyond;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BooksManager {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Book book = new Book();
		book.setTitle("effect");
		book.setAuthor("jack");
		book.setPrice(78f);
		em.persist(book);

		Query query = em.createQuery("from Book");
		List<Book> list = query.getResultList();
		System.out.println(list.get(0).getBookId());

		em.getTransaction().commit();
		em.close();

		EntityManager em2 = factory.createEntityManager();
		em2.getTransaction().begin();
		Book find = em2.find(Book.class, 1);
		System.out.println(find.getTitle());

		em2.getTransaction().commit();
		em2.close();
		factory.close();

	}

}
