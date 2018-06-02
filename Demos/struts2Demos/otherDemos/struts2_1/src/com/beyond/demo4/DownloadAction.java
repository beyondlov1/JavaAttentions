package com.beyond.demo4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class DownloadAction extends ActionSupport {

	private String filename;

	public String execute() {
		System.out.println(filename);
		return SUCCESS;
	}

	public String getFilename() throws UnsupportedEncodingException {
		String agent = ServletActionContext.getRequest().getHeader("User-agent");
		return this.getEncodeFileName(filename, agent);
	}

	private String getEncodeFileName(String filename, String agent) throws UnsupportedEncodingException {
		if (agent.toLowerCase().contains("firefox")) {
			return "=?UTF-8?B?" + Base64.encode(filename.getBytes("UTF-8")) + "?=";
		} else {
			return URLEncoder.encode(filename, "UTF-8");
		}
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContentType() {
		return ServletActionContext.getServletContext().getMimeType(filename);
	}

	public InputStream getInputStream() throws FileNotFoundException {
		String parentPath = ServletActionContext.getServletContext().getRealPath("/download");
		File file = new File(parentPath + "/" + filename);
		return new FileInputStream(file);
	}

}
