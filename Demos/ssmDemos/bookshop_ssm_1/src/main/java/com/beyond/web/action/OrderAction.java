package com.beyond.web.action;

import com.beyond.entity.Book;
import com.beyond.entity.Order;
import com.beyond.entity.User;
import com.beyond.service.BookService;
import com.beyond.service.OrderService;
import com.beyond.utils.BreakCycleUtils;
import com.beyond.utils.breakCycle.BreakCycleLazyLoadingStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderAction {

    private OrderService orderService;
    private BookService bookService;

    private BreakCycleUtils breakCycleUtils = new BreakCycleLazyLoadingStrategy();

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/getRelatedOrdersByUser")
    public @ResponseBody
    List<Order> getRelatedOrdersByUser(User user) {
        List<Order> orders = orderService.getRelatedOrdersByUser(user);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getToAcceptOrders")
    public @ResponseBody
    List<Order> getToAcceptOrders(User user) {
        List<Order> orders = orderService.getToAcceptOrders(user);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getNotAcceptedOrders")
    public @ResponseBody
    List<Order> getNotAcceptedOrders(User user) {
        List<Order> orders = orderService.getNotAcceptedOrders(user);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getAcceptedOrders")
    public @ResponseBody
    List<Order> getAcceptedOrders(User user) {
        List<Order> orders = orderService.getAcceptedOrders(user);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getExchangeNotOutOrders")
    public @ResponseBody
    List<Order> getExchangeNotOutOrders(User user) {
        List<Order> orders = orderService.getExchangeNotOutOrders(user);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getExchangeOutOrders")
    public @ResponseBody
    List<Order> getExchangeOutOrders(User user) {
        List<Order> orders = orderService.getExchangeOutOrders(user);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getExchangeNotInOrders")
    public @ResponseBody
    List<Order> getExchangeNotInOrders(User user) {
        List<Order> orders = orderService.getExchangeNotInOrders(user);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getExchangeInOrders")
    public @ResponseBody
    List<Order> getExchangeInOrders(User user) {
        List<Order> orders = orderService.getExchangeInOrders(user);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getCompletedOrders")
    public @ResponseBody
    List<Order> getCompletedOrders(User user) {
        List<Order> orders = orderService.getCompletedOrders(user);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getOrderByBook")
    public @ResponseBody
    List<Order> getOrderByBook(Book book) {
        List<Order> orders = orderService.getOrderByBook(book);

        breakCycleUtils.load(orders, 2);
        return orders;
    }

    @RequestMapping("/getAcceptOrdersByEitherBookId")
    public @ResponseBody
    Order getAcceptOrdersByEitherBookId(Book book) {
        Order order = orderService.getAcceptOrdersByEitherBookId(book);

        breakCycleUtils.load(order, 2);
        return order;
    }

    @RequestMapping("/removeOrder")
    public @ResponseBody
    String removeOrder(Order order) {
        orderService.removeOrder(order);
        return "success";
    }

    @RequestMapping("/initAddOrder")
    public String initAddOrder(Book book, Model model) {
        model.addAttribute("book", book);
        return "pages/order/addOrder";
    }

    @RequestMapping("/addOrder")
    public String addOrder(Order order) {
        orderService.addOrder(order);
        return "redirect:/book/showBookDetailById.action?id=" + order.getBook().getId();
    }

    @RequestMapping("/initAllOrders")
    public String initAllOrders() {
        return "pages/order/showAllOrders";
    }

    @RequestMapping(value = "/acceptOrder", produces = "text/plain; charset=utf-8")
    public @ResponseBody
    String acceptOrder(Order order) {
        try {
            orderService.acceptOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }

    @RequestMapping(value = "/exchangerConfirm", produces = "text/plain; charset=utf-8")
    public @ResponseBody
    String exchangerConfirm(Order order) {
        try {
            orderService.exchangerConfirm(order);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }

    @RequestMapping(value = "/ownerConfirm", produces = "text/plain; charset=utf-8")
    public @ResponseBody
    String ownerConfirm(Order order) {
        try {
            orderService.ownerConfirm(order);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }


}
