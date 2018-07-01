package bookshop_ssm;

import com.beyond.entity.Author;
import com.beyond.entity.Book;
import com.beyond.entity.User;
import com.beyond.utils.breakCycle.BreakCycleFetchLoadingStrategy;
import com.beyond.utils.breakCycle.BreakCycleFetchLoadingStrategy2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestBreakCycle {
    @Test
    public void test() {
        BreakCycleFetchLoadingStrategy2 breakCycleFetchLoadingStrategy2 = new BreakCycleFetchLoadingStrategy2();

        User user = new User();
        user.setUsername("username");
        List<Book> books = new ArrayList<>();

        Book book = new Book();
        book.setOwner(user);
        book.setName("bookName");
        Author author = new Author();
        author.setName("gulong");
        book.setAuthor(author);
        book.setOwner(user);
        books.add(book);

        user.setOwnBooks(books);
        User user1 = (User)breakCycleFetchLoadingStrategy2.load(user, 3);
        System.out.println(user1);
    }
}
