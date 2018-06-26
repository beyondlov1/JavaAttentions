package com.beyond.web.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyond.entity.Book;
import com.beyond.entity.Order;
import com.beyond.f.F;
import com.beyond.service.BookService;
import com.beyond.service.OrderService;
import com.beyond.utils.CookieUtils;
import com.beyond.utils.JsonBreakCycleUtils;

@Controller
@RequestMapping("/order")
public class OrderAction {

	private OrderService orderService;
	private BookService bookService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping("/getAcceptOrdersByBookId")
	public @ResponseBody List<Order> getAcceptOrdersByBookId(String id) {
		List<Order> acceptOrders = orderService.getAcceptOrdersByBookId(id);
		JsonBreakCycleUtils.load(acceptOrders, 2);
		return acceptOrders;
	}

	@RequestMapping("/getAcceptOrdersByExchangeBookId")
	public @ResponseBody List<Order> getAcceptOrdersByExchangeBookId(String id) {
		List<Order> acceptOrders = orderService.getAcceptOrdersByExchangeBookId(id);
		JsonBreakCycleUtils.load(acceptOrders, 2);
		return acceptOrders;
	}

	@RequestMapping("/getAcceptOrdersByEitherBookId")
	public @ResponseBody List<Order> getAcceptOrdersByEitherBookId(String id) {
		List<Order> acceptOrders = orderService.getAcceptOrdersByEitherBookId(id);
		JsonBreakCycleUtils.load(acceptOrders, 2);
		return acceptOrders;
	}

	@RequestMapping("/initAddOrder")
	public String initAddOrder(String bookId, Model model) {
		String borrowerId = CookieUtils.getCookie(F.LOGIN_USER_ID_COOKIE_KEY);
		List<Book> ownerBooks = bookService.getBookByOwnerId(borrowerId);
		// count>0的list
		List<Book> newOwnerBooks = new ArrayList<>();
		for (Book book : ownerBooks) {
			if (book.getCount() == null || book.getCount() > 0) {
				newOwnerBooks.add(book);
			}
		}
		model.addAttribute("ownerBooks", newOwnerBooks);
		model.addAttribute("bookId", bookId);
		model.addAttribute("borrowerId", borrowerId);
		return "pages/addOrder";
	}

	@RequestMapping("/addOrder")
	public String addOrder(Order order, Model model) {
		if (order.getExchangeBook() == null) {
			model.addAttribute("msg", "交換失敗,請確認選中正確交換書籍!");
			return "forward:/order/initAddOrder.action?bookId=" + order.getBook().getId();
		}
		order.setCreateTime(new Timestamp(new Date().getTime()));
		orderService.addOrder(order);
		return "redirect:/book/showAllBooks.action";
	}

	@RequestMapping("/acceptOrder")
	public String acceptOrder(Order order) {
		orderService.acceptOrder(order);
		return "redirect:/book/showBookDetailById.action?id=" + order.getBook().getId();
	}

	@RequestMapping("/cancelOrder")
	public String cancelOrder(Order order) {
		orderService.cancelOrder(order);
		return "redirect:/book/showBookDetailById.action?id=" + order.getBook().getId();
	}

	@RequestMapping("/confirmOrderBookRecive")
	public String confirmOrderBookRecive(String bookId) {
		orderService.confirmOrderBookRecive(bookId);
		return "redirect:/user/index.action";
	}

	@RequestMapping("/confirmOrderBookReciveAjax")
	public @ResponseBody String confirmOrderBookReciveAjax(String bookId) {
		orderService.confirmOrderBookRecive(bookId);
		return "true";
	}
}
