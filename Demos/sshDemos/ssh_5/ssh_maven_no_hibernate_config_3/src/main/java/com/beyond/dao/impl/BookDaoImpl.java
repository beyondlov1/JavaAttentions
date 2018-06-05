package com.beyond.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.beyond.entity.Book;
import com.beyond.entity.Page;
import com.beyond.entity.User;

public class BookDaoImpl extends BaseDaoImpl<Book> {
	public Book selectById(String id) {
		return super.selectById(Book.class, id);
	}

	public List<Book> selectAll() {
		return super.selectAll(Book.class);
	}

	@Override
	public Book selectByExample(Book user) {
		return super.selectByExample(user);
	}

	// 查询每个用户的书籍 分页
	@SuppressWarnings("unchecked")
	public List<Book> selectAllByUserPage(User user, Page<Book> page) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Book.class);
		criteria.add(Restrictions.eq("owner", user));
		List<Book> findByCriteria = (List<Book>) hibernateTemplate.findByCriteria(criteria, page.getStartRecordIndex(),
				page.getRecordsPerPage());
		return findByCriteria;
	}

	public Long count(User user) {
		Long count = hibernateTemplate.executeWithNativeSession(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Object uniqueResult = session
						.createQuery("select count(*) from Book where owner.id='" + user.getId() + "'").uniqueResult();
				return (Long) uniqueResult;
			}
		});
		return count;
	}

}
