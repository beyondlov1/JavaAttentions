# servlet 3.0 上传文件 #

    public void modifyBookWithFile2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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