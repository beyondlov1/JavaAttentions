package com.beyond.utils;

import java.util.UUID;

public class IdUtils {
	// ��ȡΨһid
	public static String getId() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().replaceAll("-", "");
	}
}
