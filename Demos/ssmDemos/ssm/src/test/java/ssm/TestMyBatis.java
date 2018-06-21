package ssm;

import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beyond.entity.Book;
import com.beyond.entity.User;
import com.beyond.mapper.BookMapper;
import com.beyond.mapper.UserMapper;

public class TestMyBatis {

	@Test
	public void testMyBatis_5() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");

		User owner = userMapper.selectById("4f2fb2bf704d11e89452107b447deebe");
		Set<Book> books = owner.getBooks();

		System.out.println(books);

		System.out.println("yes");
	}

	@Test
	public void testMyBatis_4() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		BookMapper bookMapper = (BookMapper) context.getBean("bookMapper");

		Book book = new Book();
		book.setName("luxiaofeng2");
		book.setPrice(100.2);
		User owner = userMapper.selectById("4f2fb2bf704d11e89452107b447deebe");
		book.setOwner(owner);
		bookMapper.addBook(book);

		System.out.println("yes");
	}

	@Test
	public void testMyBatis_3() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper mapper = (UserMapper) context.getBean("userMapper");

		Set<User> foundUsers = mapper.selectAll();
		System.out.println(foundUsers);

	}

	@Test
	public void testMyBatis_2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper mapper = (UserMapper) context.getBean("userMapper");
		User user = new User();
		user.setUsername("beyond");
		user.setPassword("beyondlov1");
		Set<User> foundUsers = mapper.selectByExample(user);
		System.out.println(foundUsers);

	}

	@Test
	public void testMyBatis_1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper mapper = (UserMapper) context.getBean("userMapper");
		User user = new User();
		user.setUsername("beyond");
		user.setPassword("beyondlov1");
		mapper.addUser(user);

	}
}
