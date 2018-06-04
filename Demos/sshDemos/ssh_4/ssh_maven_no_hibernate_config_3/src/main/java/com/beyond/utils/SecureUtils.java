package com.beyond.utils;

import java.security.MessageDigest;
import java.util.Base64;

public class SecureUtils {
	// ”√md5º”√‹√‹¬Î
	public static String getEncryptString(String plainText) {
		if (plainText == null || "".equals(plainText)) {
			return null;
		}
		String encryptString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] b = md.digest(plainText.getBytes());
			encryptString = Base64.getEncoder().encodeToString(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encryptString;
	}
}
