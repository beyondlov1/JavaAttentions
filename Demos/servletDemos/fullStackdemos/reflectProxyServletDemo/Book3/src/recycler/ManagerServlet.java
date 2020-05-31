package recycler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyond.proxy.ServiceFactory;

@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {

	public ManagerServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String operator = request.getParameter("op");
		if ("login".equals(operator)) {
			login(request, response);
		}

		if ("signup".equals(operator)) {
			signup(request, response);
		}

		if ("showBook".equals(operator)) {
			showBook(request, response);
		}

		if ("addBook".equals(operator)) {
			addBook(request, response);
		}

		if ("showAddBook".equals(operator)) {
			showAddBook(request, response);
		}

	}

	// 显示添加图书界面
	private void showAddBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Service service = (Service) ServiceFactory.getInstance().getService(ServiceImpl.class);
		service.initAddBook(request, response);
	}

	// 添加图书
	private void addBook(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Service service = (Service) ServiceFactory.getInstance().getService(ServiceImpl.class);
		service.addBook(request, response);
	}

	// 显示图书
	private void showBook(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Service service = (Service) ServiceFactory.getInstance().getService(ServiceImpl.class);
		service.showBook(request, response);
	}

	// 注册
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = (Service) ServiceFactory.getInstance().getService(ServiceImpl.class);
		service.signup(request, response);
	}

	// 登录
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = (Service) ServiceFactory.getInstance().getService(ServiceImpl.class);
		service.login(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
