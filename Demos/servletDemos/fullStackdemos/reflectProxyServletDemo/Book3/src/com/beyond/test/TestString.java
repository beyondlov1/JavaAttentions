package com.beyond.test;

public class TestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "category";
		String b = "categoryid";
		System.out.println(a.indexOf(b));
		System.out.println(b.indexOf(a));
		System.out.println(b.substring(b.indexOf(a) + a.length()));
	}

}
