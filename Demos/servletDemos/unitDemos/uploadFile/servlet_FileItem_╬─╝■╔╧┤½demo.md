# servlet_FileItem_文件上传 #

    public void addBookWithFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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