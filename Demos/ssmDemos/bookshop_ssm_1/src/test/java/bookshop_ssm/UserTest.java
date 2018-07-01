package bookshop_ssm;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beyond.entity.Author;
import com.beyond.entity.Book;
import com.beyond.entity.Order;
import com.beyond.entity.User;
import com.beyond.mapper.AuthorMapper;
import com.beyond.mapper.BookMapper;
import com.beyond.mapper.OrderMapper;
import com.beyond.mapper.UserMapper;
import com.beyond.service.BookService;

public class UserTest {

	@Test
	public void testSelectBookByUserId() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");
		BookService bookService = (BookService) context.getBean("bookService");

		String userId = "49791d7f762c11e8bfa8107b447deebe";

		List<Book> list = bookService.getBookByOwnerId(userId);
		System.out.println(list.get(0).getId());
	}

	@Test
	public void testSelectByBookExample() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		Book book = new Book();
		// book.setName("luxiaofeng");
		User owner = userMapper.selectById("fbc4037c762811e8bfa8107b447deebe");
		book.setOwner(owner);
		List<Book> list = bookMapper.selectByExampleBlur(book);
		System.out.println(list.get(0).getId());
	}

	@Test
	public void testDeleteUser() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		User user = new User();
		user.setUsername("beyond3");
		user.setPassword("beyondlov1");
		User foundUser = userMapper.selectByExample(user);
		userMapper.delete(foundUser);
		System.out.println(foundUser.getUsername());
	}

	@Test
	public void testUpdateUser() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		User user = new User();
		user.setUsername("beyond");
		user.setPassword("beyondlov1");
		User foundUser = userMapper.selectByExample(user);
		foundUser.setUsername("beyond3");
		userMapper.update(foundUser);
		System.out.println(foundUser.getUsername());
	}

	@Test
	public void testSelectAll() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		List<User> allUsers = userMapper.selectAll();
		System.out.println(allUsers);
	}

	@Test
	public void testSelectByUserExample() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		User user = new User();
		user.setUsername("beyond");
		user.setPassword("beyondlov1");

		User foundUser = userMapper.selectByExample(user);
		System.out.println(foundUser.getId());
	}

	@Test
	public void testSelectByBookId() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		Book book = bookMapper.selectById("fbc4037c762811e8bfa8107b447deebe");
		System.out.println(book.getOrders().get(0).getExchanger().getUsername());
	}

	@Test
	public void testSelectByUserId() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		User user = userMapper.selectById("c979c371761f11e8bfa8107b447deebe");
		System.out.println(user.getReciveOrders().get(0).getBook().getName());
	}

	@Test
	public void testAddOrder() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		User user = userMapper.selectById("4ee5a56d762c11e8bfa8107b447deebe");
		Book book = bookMapper.selectById("638c6c15762c11e8bfa8107b447deebe");

		Order order = new Order();
		order.setBook(book);
		order.setExchanger(user);
		orderMapper.add(order);
	}

	@Test
	public void testAddAuthor() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		AuthorMapper authorMapper = (AuthorMapper) context.getBean("authorMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		Author author = new Author();
		author.setId("123");
		author.setName("gulong");
		authorMapper.add(author);
	}

	@Test
	public void testAddBook() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");
		AuthorMapper authorMapper = (AuthorMapper) context.getBean("authorMapper");

		User owner = userMapper.selectById("49791d7f762c11e8bfa8107b447deebe");

		Book book = new Book();
		book.setName("chuliuxiang1");
		book.setPrice(33.3);
		book.setOwner(owner);

		bookMapper.add(book);
	}

	@Test
	public void testAddUser() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

		User user = new User();
		user.setUsername("beyond1");
		user.setPassword("beyondlov1");

		userMapper.add(user);
	}

}
