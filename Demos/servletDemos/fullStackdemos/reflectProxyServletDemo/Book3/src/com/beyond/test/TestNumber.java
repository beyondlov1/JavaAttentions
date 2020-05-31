package com.beyond.test;

public class TestNumber {
	public static void main(String[] args) {
		Double d = 0.0;
		double dd = 0;

		Object o = dd;
		System.out.println(o instanceof Number);
		Number n = (Number) o;
		System.out.println(n.equals(0.0));

		System.out.println(0 == Double.parseDouble(new Integer(0).toString()));
	}
}
