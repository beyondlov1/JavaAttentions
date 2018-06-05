package com.beyond.utils;

import java.io.File;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.beyond.f.F;

public class FileUtil {

	// 返回存储的uri（不包括项目名）
	public static String simpleUploadFile(File upload, String uploadRoot, String uploadFileName) {
		// 文件上传
		String parentPath = null;
		File parentFile = null;
		String filePath = null;
		File file = null;
		String uri = null;

		// 获取copy目标
		parentPath = ServletActionContext.getServletContext().getRealPath("/")
				+ DirUtils.getParentUri(F.UPLOADROOT, uploadFileName);
		parentFile = new File(parentPath);
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		filePath = parentPath + "/" + uploadFileName;
		file = new File(filePath);

		// copy
		try {
			FileUtils.copyFile(upload, file);
			uri = DirUtils.getUri(F.UPLOADROOT, uploadFileName);
			uri = URLEncoder.encode(uri, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return uri;
	}

}
