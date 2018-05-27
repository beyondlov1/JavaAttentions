package com.beyond.test;

import com.beyond.entity.Page;

public class TestPage {

	public static void main(String[] args) {
		Page page = new Page(3, 32);
		System.out.println(page.getDisplayEndPageNum());
	}
}
