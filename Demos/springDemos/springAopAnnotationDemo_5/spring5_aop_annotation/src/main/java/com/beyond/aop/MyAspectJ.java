package com.beyond.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspectJ {

	@Before("execution(* com.beyond.service.*.*(..))")
	public void before(JoinPoint joinPoint) {
		System.out.println("i am before of aspectJ");
	}

	@After("execution(* com.beyond.service.*.*(..))")
	public void after(JoinPoint joinPoint) {
		System.out.println("i am after of aspectJ");
	}

	@Around("execution(* com.beyond.service.*.*(..))")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("i am before around of aspectJ");
		joinPoint.proceed();
		System.out.println("i am after around of aspectJ");
	}

	@AfterReturning(pointcut = "execution(* com.beyond.service.*.*(..))", returning = "returnValue")
	public void afterReturning(JoinPoint joinPoint, Object returnValue) {
		System.out.println("i am afterReturning of aspectJ");
		System.out.println("i am returning:" + returnValue);
	}

	@AfterThrowing(pointcut = "execution(* com.beyond.service.*.*(..))", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		System.out.println("i am afterThrowing of aspectJ");
		System.out.println("i am exception:" + e.getMessage());
	}
}
