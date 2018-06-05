package com.beyond.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class IOBaseAction extends BaseAction {

	// 上传相关(上传的input name 必须是 upload 才有效， 默认上传存储位置根目录为本项目的 upload 文件夹)
	// 注意：存在项目里重启TOMCAT会清除文件夹！！！！
	protected File upload;
	protected String uploadContentType;
	protected String uploadFileName;

	// 下载相关
	protected String filename;
	protected String uri;

	// 下载
	public String getFilename() throws UnsupportedEncodingException {
		String agent = ServletActionContext.getRequest().getHeader("User-agent");
		return this.getEncodeFileName(filename, agent);
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private String getEncodeFileName(String filename, String agent) throws UnsupportedEncodingException {
		if (agent.toLowerCase().contains("firefox")) {
			return "=?UTF-8?B?" + Base64.encode(filename.getBytes("UTF-8")) + "?=";
		} else {
			return URLEncoder.encode(filename, "UTF-8");
		}
	}

	public String getContentType() {
		return ServletActionContext.getServletContext().getMimeType(filename);
	}

	public InputStream getInputStream() throws FileNotFoundException, UnsupportedEncodingException {
		String parentPath = ServletActionContext.getServletContext().getRealPath("/");
		File file = new File(parentPath + "/" + URLDecoder.decode(uri, "UTF-8"));
		return new FileInputStream(file);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
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
