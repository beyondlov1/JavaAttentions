package com.beyond.test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("a", 984);
		System.out.println(map.get("b") == null);
	}

}
