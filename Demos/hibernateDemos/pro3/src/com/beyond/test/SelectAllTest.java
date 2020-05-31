package com.beyond.test;

import java.util.List;

import org.junit.Test;

import com.beyond.dao.Dao;
import com.beyond.entity.Customer;

public class SelectAllTest {

	@Test
	public void test() {
		Dao dao = new Dao();
		List<Customer> selectAll = dao.selectAll();
		System.out.println(selectAll);
	}
}
