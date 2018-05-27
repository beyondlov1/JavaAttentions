package com.beyond.utils;

import java.security.MessageDigest;

import com.beyond.exception.NullPasswordException;

import sun.misc.BASE64Encoder;

public class SecureUtils {
	// ”√md5º”√‹√‹¬Î
	public static String getEncryptString(String plainText) throws NullPasswordException {
		if (plainText == null || "".equals(plainText)) {
			throw new NullPasswordException("√‹¬ÎŒ™ø’");
		}
		String encryptString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] b = md.digest(plainText.getBytes());
			BASE64Encoder base64Encoder = new BASE64Encoder();
			encryptString = base64Encoder.encode(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encryptString;
	}
}
