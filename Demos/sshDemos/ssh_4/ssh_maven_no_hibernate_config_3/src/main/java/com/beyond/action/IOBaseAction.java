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

	// �ϴ����(�ϴ���input name ������ upload ����Ч�� Ĭ���ϴ��洢λ�ø�Ŀ¼Ϊ����Ŀ�� upload �ļ���)
	// ע�⣺������Ŀ������TOMCAT������ļ��У�������
	protected File upload;
	protected String uploadContentType;
	protected String uploadFileName;

	// �������
	protected String filename;
	protected String uri;

	// ����
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
