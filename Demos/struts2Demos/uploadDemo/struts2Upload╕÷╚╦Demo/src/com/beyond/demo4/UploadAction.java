package com.beyond.demo4;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {

	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	public String upload() {

		String parentPath = ServletActionContext.getServletContext().getRealPath("/") + "/upload";
		File parentFile = new File(parentPath);
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		String filePath = parentPath + "/" + uploadFileName;
		File file = new File(filePath);

		try {
			System.out.println(uploadContentType);
			FileUtils.copyFile(upload, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}
