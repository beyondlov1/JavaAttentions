package com.beyond.utils;

import java.util.UUID;

public class IdUtils {
	// ªÒ»°Œ®“ªid
	public static String getId() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().replaceAll("-", "");
	}
}
