package com.beyond.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import com.beyond.dao.BaseDao;
import com.beyond.dao.impl.BaseDaoImpl;
import com.beyond.entity.Book;
import com.beyond.entity.BookForSql;
import com.beyond.entity.Category;
import com.beyond.entity.CategoryForSql;

public class DAOFactory {

	private static DAOFactory dao = null;

	private DAOFactory() {

	}

	public static DAOFactory getInstance() {
		if (dao == null) {
			dao = new DAOFactory();
		}
		return dao;
	}

	public BaseDao getBaseDao() {
		BaseDao bd = new BaseDaoImpl();
		return (BaseDao) Proxy.newProxyInstance(bd.getClass().getClassLoader(), bd.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

						for (int i = 0; i < args.length; i++) {
							Object arg = args[i];

							// 1. ��������к���Book.class, Book book ����ForSql�Ķ��Ĳ���
							if (arg instanceof Book) {
								args[i] = convert(BookForSql.class, arg);
							}

							if (arg instanceof Category) {
								args[i] = convert(CategoryForSql.class, arg);
							}

							if (arg instanceof Class) {

								if (arg.equals(Book.class)) {
									args[i] = BookForSql.class;
									// 2. �������Ϊselect����ķ���ֵ����
									if (method.getName().equals("selectBean")) {
										Object invoke = method.invoke(bd, args);
										// ִ�з���
										if (invoke != null) {
											return convert(Book.class, invoke);
										} else {
											return null;
										}
									}

									if (method.getName().equals("selectAllBean")) {

										Object invoke = method.invoke(bd, args);

										// ִ�з���
										if (invoke != null) {
											return convertList(Book.class, invoke);
										} else {
											return null;
										}
									}
								}

								if (arg.equals(Category.class)) {
									args[i] = CategoryForSql.class;
									// 2. �������Ϊselect����ķ���ֵ����
									if (method.getName().equals("selectBean")) {
										Object invoke = method.invoke(bd, args);
										// ִ�з���
										if (invoke != null) {
											return convert(Category.class, invoke);
										} else {
											return null;
										}
									}

									if (method.getName().equals("selectAllBean")) {
										Object invoke = method.invoke(bd, args);
										// ִ�з���
										if (invoke != null) {
											return convertList(Category.class, invoke);
										} else {
											return null;
										}
									}
								}
							}
						}

						// if (method.getName().equals("addBean")) {
						// // �滻����
						// for (int i = 0; i < args.length; i++) {
						// Object arg = args[i];
						// if (arg instanceof Book) {
						// args[i] = ConvertBook2BookSql(arg);
						// }
						// }
						// return method.invoke(bd, args);
						// }
						//
						// if (method.getName().equals("selectBean")) {
						// // �����������ΪBookForSql��ִ�л�����,������ֵ�ķ���
						// for (int i = 0; i < args.length; i++) {
						// Object arg = args[i];
						// // �滻����
						// if (arg instanceof Class) {
						// args[i] = BookForSql.class;
						// if (arg.equals(Book.class)) {
						// args[i] = BookForSql.class;
						// // ִ�з���
						// return ConvertBookSql2Book(method.invoke(bd, args));
						// }
						// }
						// }
						// }

						return method.invoke(bd, args);
					}

				});
	}

	@SuppressWarnings("unchecked")
	private <T, E> List<T> convertList(Class<T> c, Object sqlList) {
		List<T> list = null;

		if (sqlList instanceof List) {
			list = new ArrayList<>();
			List<E> inList = (List<E>) sqlList;
			// ��list�е�ȡ�������ת��
			for (E item : inList) {
				T t = convert(c, item);

				list.add(t);
			}
		}

		return list;
	}

	public <T, E> T convert(Class<T> c, E e) {
		T t = null;
		BaseDao baseDao = null;
		try {
			t = c.newInstance();
			baseDao = getBaseDao();
			// 1. ��ȡfields��fieldName
			Field[] tf = c.getDeclaredFields();
			Field[] ef = e.getClass().getDeclaredFields();
			String[] tfname = new String[tf.length];
			String[] efname = new String[ef.length];
			for (int i = 0; i < tf.length; i++) {
				tfname[i] = tf[i].getName();
				tf[i].setAccessible(true);
			}
			for (int i = 0; i < ef.length; i++) {
				efname[i] = ef[i].getName();
				ef[i].setAccessible(true);
			}

			// 2. �Ƚ�����field, ���name��ͬ��ֱ�Ӹ�ֵ, �����ͬ���ж�ת��
			for (int i = 0; i < tfname.length; i++) {
				boolean isRepeat = false;
				for (int j = 0; j < efname.length; j++) {
					if (tfname[i].equals(efname[j])) {

						tf[i].set(t, ef[j].get(e));
						isRepeat = true;
						break;
					}
				}
				if (!isRepeat) {
					// ���Ҷ�Ӧ���ֶ�
					for (int j = 0; j < efname.length; j++) {

						// tfname���� ef���Ǹ�����
						if (tfname[i].indexOf(efname[j]) != -1) {
							// ��ȡef�ж�Ӧ�ֶε�name
							String lastName = tfname[i].substring(tfname[i].indexOf(efname[j]) + efname[j].length())
									.toLowerCase();

							Object fieldValue = ef[j].get(e);
							if (fieldValue != null) {
								// ��ȡef�ж�Ӧ��field
								Field idField = ef[j].getType().getDeclaredField(lastName);
								idField.setAccessible(true);

								// ��ef�ж�Ӧ�ֶε�ֵ����t��tf[i]�ֶ�
								tf[i].set(t, idField.get(fieldValue));
							}
						}

						// efname���� tf���Ǹ�����
						if (efname[j].indexOf(tfname[i]) != -1) {

							// ��ȡ��׺,����: categoryid�е�id
							String lastName = efname[j].substring(efname[j].indexOf(tfname[i]) + tfname[i].length())
									.toLowerCase();

							// ����ef��dao�в���һ��tf����
							Object selectBean = null;
							Object fieldValue = ef[j].get(e);
							if (fieldValue != null) {
								selectBean = baseDao.selectBean(tf[i].getType(), lastName, fieldValue);
							}

							// �����󴫸�t
							tf[i].set(t, selectBean);
						}
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException
				| NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 3. ���ض���
		return t;
	}

	public static BookForSql ConvertBook2BookSql(Object object) {
		BookForSql bfs = new BookForSql();

		if (object instanceof Book) {
			Book book = (Book) object;
			bfs.setId(book.getId());
			bfs.setName(book.getName());
			bfs.setDescription(book.getDescription());
			bfs.setAuthor(book.getAuthor());
			bfs.setWordCount(book.getWordCount());
			bfs.setPrice(book.getPrice());
			bfs.setOwnerId(book.getOwnerId());
			bfs.setCategoryId(book.getCategory().getId());
		}
		return bfs;
	}

	private Book ConvertBookSql2Book(Object object) {
		Book book = new Book();
		BaseDao baseDao = getBaseDao();
		if (object instanceof Book) {
			BookForSql bfs = (BookForSql) object;
			book.setId(bfs.getId());
			book.setName(bfs.getName());
			book.setDescription(bfs.getDescription());
			book.setAuthor(bfs.getAuthor());
			book.setWordCount(bfs.getWordCount());
			book.setPrice(bfs.getPrice());
			book.setOwnerId(bfs.getOwnerId());
			book.setCategory(baseDao.selectBean(Category.class, bfs.getCategoryId()));
		}
		return book;
	}
}
