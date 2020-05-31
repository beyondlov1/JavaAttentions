package com.beyond.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import com.beyond.entity.Author;
import com.beyond.entity.Book;
import com.beyond.entity.Order;
import com.beyond.entity.User;

/*
 * 用来解决生成json对象时死循环的现象
 * 使用方法: obj代表要生成的对象, depth代表要加载的深度 本案例推荐3,基本可覆盖所有信息
 */
public class JsonBreakCycleUtils {

	public static void load(Object obj, int depth) {
		try {
			if (obj instanceof Collection) {
				Collection collection = (Collection) obj;
				if (collection != null && collection.size() > 0) {
					for (Object object : collection) {
						load(object, depth);
					}
				}
			}
			Method[] methods = obj.getClass().getMethods();
			for (Method method : methods) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				Class<?> returnType = method.getReturnType();

				if (Collection.class.isAssignableFrom(returnType) && method.getName().startsWith("get")) {
					Collection collection = (Collection) method.invoke(obj);

					if (collection != null && collection.size() > 0) {
						for (Object object : collection) {
							if (depth > 0) {
								load(object, depth - 1);
							} else {
								setNull(obj, method);
							}
						}
					}
				}

				if (isMyClass(returnType) && method.getName().startsWith("get")) {
					Object object = method.invoke(obj);
					if (object != null) {
						if (depth > 0) {
							load(object, depth - 1);
						} else {
							setNull(obj, method);
						}
					}
				}
			}
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private static boolean isMyClass(Class clazz) {

		if (User.class.isAssignableFrom(clazz)) {
			return true;
		}
		if (Book.class.isAssignableFrom(clazz)) {
			return true;
		}
		if (Author.class.isAssignableFrom(clazz)) {
			return true;
		}
		if (Order.class.isAssignableFrom(clazz)) {
			return true;
		} else {
			return false;
		}

	}

	private static void setNull(Object obj, Method method) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String substring = method.getName().substring(3);
		String setMethodName = "set" + substring;

		Method[] methods = obj.getClass().getMethods();
		for (Method m : methods) {
			if (m.getName().equals(setMethodName)) {
				m.invoke(obj, new Object[] { null });
			}
		}
	}
}
