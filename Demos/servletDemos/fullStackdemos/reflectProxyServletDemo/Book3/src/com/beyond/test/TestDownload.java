package com.beyond.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDownload")
public class TestDownload extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getParameter("filePath");

		File file = null;
		if (path == null) {
			file = new File(this.getServletContext().getRealPath("/") + "uploadFiles");
		} else {
			path = URLDecoder.decode(path, "UTF-8");
			file = new File(path);
		}

		if (file.isDirectory()) {
			String filePath = file.getPath();
			request.setAttribute("filePath", filePath);
			request.getRequestDispatcher("/many_download.jsp").forward(request, response);
		} else {
			System.out.println(file.getName());

			// 解决下载中文名称乱码的问题 chrome firefox 不同
			String userAgent = request.getHeader("user-agent");
			String encodedName = null;
			if (userAgent.toLowerCase().contains("chrome")) {
				encodedName = URLEncoder.encode(file.getName(), "UTF-8");
			} else if (userAgent.toLowerCase().contains("firefox")) {
				encodedName = "=?UTF-8?B?" + new String(Base64.getEncoder().encode(file.getName().getBytes("UTF-8")))
						+ "?=";
			} else {
				throw new RuntimeException("NO IE!");
			}

			String mimeType = this.getServletContext().getMimeType(file.getName());
			response.setContentType(mimeType);
			response.setHeader("content-disposition", "attachment;filename=" + encodedName);

			FileInputStream fos = new FileInputStream(file);
			ServletOutputStream os = response.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(fos);
			int len = 0;
			byte[] b = new byte[bis.available()];
			while ((len = bis.read(b)) != -1) {
				os.write(b, 0, len);
				os.flush();
			}
			bis.close();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
