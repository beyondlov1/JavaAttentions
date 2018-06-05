package com.beyond.utils;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class DirUtils {
	public static String getDir(String filename) {
		int hashCode = filename.hashCode();
		int dir1 = hashCode & 0xf;
		int dir2 = (hashCode >>> 4) & 0xf;

		return File.separator + dir1 + File.separator + dir2;
	}

	public static String getAbsoluteDir(HttpServletRequest request, String root, String filename) {
		return request.getServletContext().getRealPath("/" + root) + getDir(filename);
	}

	public static String getUri(String root, String storageFileName) {
		return root + getDir(storageFileName) + File.separator + storageFileName;
	}

	public static String getParentUri(String root, String storageFileName) {
		return root + getDir(storageFileName) + File.separator;
	}

	public static String getUniqueFileName(String oldFileName) {
		return UUID.randomUUID().toString() + "_" + oldFileName;
	}
}
