package com.beyond.servlet.reflect;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beyond.entity.Book;
import com.beyond.entity.BookForSql;
import com.beyond.entity.Category;
import com.beyond.entity.Page;
import com.beyond.exception.IllegalArgumentException;
import com.beyond.proxy.ServiceFactory;
import com.beyond.service.BookService;
import com.beyond.service.CategoryService;
import com.beyond.service.impl.BookServiceImpl;
import com.beyond.service.impl.CategoryServiceImpl;
import com.beyond.utils.BeanUtils;
import com.beyond.utils.DirUtils;

@MultipartConfig
@WebServlet(urlPatterns = "/BookServlet")
public class BookServlet extends BaseServlet {

	public static final Logger log = LogManager.getLogger();

	ServiceFactory sf = ServiceFactory.getInstance();

	@Deprecated
	public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookForSql book = BeanUtils.fillBean(request, BookForSql.class);
		BookService bs = (BookService) sf.getService(BookServiceImpl.class);

		try {
			bs.saveBook(book);
			response.sendRedirect(request.getContextPath() + "/BookServlet?op=showBook");
			// response.setHeader("refresh", "0;url=" + request.getContextPath()
			// + "/BookServlet?op=showBook");
		} catch (IllegalArgumentException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/BookServlet?op=initAddBook").forward(request, response);
		}

	}

	public void showBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = (BookService) sf.getService(BookServiceImpl.class);

		// 接收数据
		String currentPageNum = request.getParameter("currentPageNum");
		if (currentPageNum == null || "".equals(currentPageNum)) {
			currentPageNum = "1";
		}

		// 业务
		Page<Book> page = null;
		String url = request.getContextPath() + "/BookServlet?op=showBook";
		page = bs.findAll(url, currentPageNum);

		// 分发
		request.setAttribute("page", page);
		request.getRequestDispatcher("/show_book.jsp").forward(request, response);

	}

	public void removeBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = (BookService) sf.getService(BookServiceImpl.class);

		String id = request.getParameter("id");
		bs.removeBook(id);
		response.sendRedirect(request.getContextPath() + "/BookServlet?op=showBook");
	}

	public void initModifyBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = (BookService) sf.getService(BookServiceImpl.class);

		String id = request.getParameter("id");
		Book book = bs.findBookById(id);

		// 初始化分类
		CategoryService cs = (CategoryService) ServiceFactory.getInstance().getService(CategoryServiceImpl.class);
		List<Category> categoryList = cs.findAll();
		request.setAttribute("category_list", categoryList);

		request.setAttribute("book", book);
		request.getRequestDispatcher("/modify_book.jsp").forward(request, response);
	}

	@Deprecated
	public void modifyBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = (BookService) sf.getService(BookServiceImpl.class);

		BookForSql book = BeanUtils.fillBean(request, BookForSql.class);
		Map<String, Object> map = null;
		try {
			map = BeanUtils.getNoNullMapFromBean(book);
			if (map == null) {
				throw new RuntimeException("不合法输入");
			}
			System.out.println(map.get("price").toString());
			if (map.get("price") != null && 0 == Double.parseDouble(map.get("price").toString())) {
				throw new RuntimeException("price-输入错误");
			}
			map.put("modifyDate", new Timestamp(Calendar.getInstance().getTimeInMillis()));
			bs.modifyBook(book.getId(), map);
			response.sendRedirect(request.getContextPath() + "/BookServlet?op=showBook");
		} catch (Exception e1) {
			request.setAttribute("msg", e1.getMessage());
			request.getRequestDispatcher("/BookServlet?op=initModifyBook").forward(request, response);
		}

	}

	public void modifyBookWithFile2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = (BookService) sf.getService(BookServiceImpl.class);

		// 封装普通数据
		BookForSql book = BeanUtils.fillBean(request, BookForSql.class);
		Map<String, Object> map = null;
		try {
			map = BeanUtils.getNoNullMapFromBean(book); // 只封装非空的修改数据

			// 确定表单的合法性
			if (map == null) {
				throw new RuntimeException("不合法输入");
			}
			if (map.get("price") != null && 0 == Double.parseDouble(map.get("price").toString())) {
				throw new RuntimeException("price-输入错误");
			}

			// 上传文件
			Part part = request.getPart("upload");
			String uploadFileName = part.getSubmittedFileName();
			if (uploadFileName != null && !"".equals(uploadFileName)) {
				String storageFileName = DirUtils.getUniqueFileName(uploadFileName);
				String parentPath = DirUtils.getAbsoluteDir(request, "books", storageFileName);
				String filePath = parentPath + File.separator + storageFileName;
				String fileUri = DirUtils.getUri("books", storageFileName);

				File parent = new File(parentPath);
				if (!parent.exists()) {
					parent.mkdirs();
				}
				part.write(filePath);

				map.put("fileUri", fileUri);
				map.put("fileName", uploadFileName);
			}

			map.put("modifyDate", new Timestamp(Calendar.getInstance().getTimeInMillis()));
			bs.modifyBook(book.getId(), map);
			response.sendRedirect(request.getContextPath() + "/BookServlet?op=showBook");
		} catch (Exception e1) {
			request.setAttribute("msg", e1.getMessage());
			request.getRequestDispatcher("/BookServlet?op=initModifyBook").forward(request, response);
		}

	}

	public void initAddBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 初始化分类
		CategoryService cs = (CategoryService) ServiceFactory.getInstance().getService(CategoryServiceImpl.class);
		List<Category> categoryList = cs.findAll();

		// 分发
		request.setAttribute("category_list", categoryList);
		request.getRequestDispatcher("/add_book.jsp").forward(request, response);
	}

	@Deprecated
	public void addBookWithFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = (BookService) sf.getService(BookServiceImpl.class);

		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new RuntimeException("not multipart!");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");// 解决中文乱码
		Map<String, String> map = new HashMap<>();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					map.put(name, value);
				} else {
					String storageFileName = DirUtils.getUniqueFileName(item.getName());
					String parentPath = DirUtils.getAbsoluteDir(request, "books", storageFileName);
					String fileUri = DirUtils.getUri("books", storageFileName);
					File parentDir = new File(parentPath);
					File file = new File(parentPath + File.separator + storageFileName);

					if (!parentDir.exists()) {
						parentDir.mkdirs();
					}

					item.write(file);
					item.delete();

					map.put("fileUri", fileUri);
					map.put("fileName", item.getName());
				}
			}

			Date time = Calendar.getInstance().getTime();
			java.sql.Timestamp timestamp = new Timestamp(time.getTime());
			map.put("createDate", timestamp.toString());
			map.put("modifyDate", timestamp.toString());
			System.out.println(map);
			BookForSql book = new BookForSql();
			org.apache.commons.beanutils.BeanUtils.populate(book, map);
			bs.saveBook(book);
			response.sendRedirect(request.getContextPath() + "/BookServlet?op=showBook");
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/BookServlet?op=initAddBook").forward(request, response);
			e.printStackTrace();
		}

	}

	public void addBookWithFile2(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		BookService bs = (BookService) sf.getService(BookServiceImpl.class);

		// 封装普通数据
		BookForSql book = BeanUtils.fillBean(request, BookForSql.class);

		try {
			// 获取文件名
			Part part = request.getPart("upload");
			String uploadFileName = part.getSubmittedFileName();

			// 如果有文件上传
			if (!"".equals(uploadFileName)) {
				// 验证文件类型
				String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
				if (!".txt".equals(fileType)) {
					throw new RuntimeException("only txt");
				}
				log.info(fileType);

				String storageFileName = DirUtils.getUniqueFileName(uploadFileName);
				String parentPath = DirUtils.getAbsoluteDir(request, "books", storageFileName);
				String filePath = parentPath + File.separator + storageFileName;
				String fileUri = DirUtils.getUri("books", storageFileName);

				File parentDir = new File(parentPath);
				if (!parentDir.exists()) {
					parentDir.mkdirs();
				}
				part.write(filePath);

				book.setFileName(uploadFileName);
				book.setFileUri(fileUri);
			}

			bs.saveBook(book);
			response.sendRedirect(request.getContextPath() + "/BookServlet?op=showBook");
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/BookServlet?op=initAddBook").forward(request, response);
			e.printStackTrace();
		}
	}
}
