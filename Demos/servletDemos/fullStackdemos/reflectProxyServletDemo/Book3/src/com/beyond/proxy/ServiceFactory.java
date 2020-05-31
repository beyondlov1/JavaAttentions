package com.beyond.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.beyond.utils.JDBCUtils;

public class ServiceFactory {
	// ����ģʽ
	private static ServiceFactory serviceFactory = new ServiceFactory();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return serviceFactory;
	}

	public Object getService(Class<?> c) {

		Object proxyObject = null;
		try {
			final Object object = c.newInstance();
			proxyObject = Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),
					new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

							System.out.println("Proxy start");
							long begin = System.currentTimeMillis();
							// ��������
							// JDBCUtils.startTransaction(); // ��ʱ ���3 millis

							// ִ�з���
							Object obj = null;
							try {
								obj = method.invoke(object, args);
							} catch (InvocationTargetException e) {
								// JDBCUtils.rollback();
								throw e.getCause();
							} finally {
								// �ύ + �ͷ���Դ
								JDBCUtils.release();
							}

							long end = System.currentTimeMillis();
							System.out.println(method.getName() + ": " + (end - begin) + "mills");
							return obj;
						}
					});
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}

		return proxyObject;
	}
}
