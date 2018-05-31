package com.beyond.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class BookServiceAspect implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("i am before method");
		Object object = invocation.proceed();
		System.out.println("i am after method");
		return object;
	}

}
