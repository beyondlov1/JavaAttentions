package com.beyond.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.beyond.utils.DirUtils;

@WebServlet("/TestUpload")
public class TestUpload extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new RuntimeException("亲，您的enctype属性设置的不正确！！");
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload();
		upload.setHeaderEncoding("UTF-8");
		upload.setFileItemFactory(factory);
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem item : fileItems) {
				if (item.isFormField()) {
					item.getString();
				} else {
					String name = item.getFieldName();
					System.out.println(name);

					SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
					// 设置保存路径
					File parent = new File(this.getServletContext().getRealPath("/") + "uploadFiles" + File.separator
							+ format.format(Calendar.getInstance().getTime()) + DirUtils.getDir(name));
					if (!parent.exists()) {
						parent.mkdirs();
					}
					File file = new File(parent, item.getName());
					item.write(file);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
