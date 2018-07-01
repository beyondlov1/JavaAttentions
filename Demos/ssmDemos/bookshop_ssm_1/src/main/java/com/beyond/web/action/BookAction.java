package com.beyond.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.beyond.entity.Book;
import com.beyond.entity.User;
import com.beyond.f.F;
import com.beyond.service.BookService;
import com.beyond.service.OrderService;
import com.beyond.utils.CookieUtils;
import com.beyond.utils.DirUtils;
import com.beyond.utils.JsonBreakCycleUtils;

@Controller
@RequestMapping("/book")
public class BookAction {

    private BookService bookService;
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping("/getBookByOwner")
    public @ResponseBody
    List<Book> getBookByOwner(User owner) {
        List<Book> list = bookService.getBookByOwner(owner);
        JsonBreakCycleUtils.load(list, 3);
        return list;
    }

    @RequestMapping("/getAvailableBookByOwner")
    public @ResponseBody
    List<Book> getAvailableBookByOwner(User owner) {
        List<Book> list = bookService.getAvailableBookByOwner(owner);
        JsonBreakCycleUtils.load(list, 3);
        return list;
    }

    @RequestMapping("/getBookByBorrower")
    public @ResponseBody
    List<Book> getBookByBorrower(User borrower) {
        List<Book> list = bookService.getBookByBorrower(borrower);
        JsonBreakCycleUtils.load(list, 3);
        return list;
    }

    @RequestMapping("/getUserDetail")
    public @ResponseBody
    User getUserDetail(String id) {
        User userDetail = bookService.getUserDetail(id);
        JsonBreakCycleUtils.load(userDetail, 3);
        return userDetail;
    }

    @RequestMapping("/getAllBooks")
    public @ResponseBody
    List<Book> getAllBooks() {
        List<Book> list = bookService.getAllBooks();
        List<Book> newList = new ArrayList<>();
        for (Book book : list) {
            if (book.getCount() == null || book.getCount() > 0) {
                newList.add(book);
            }
        }
        JsonBreakCycleUtils.load(newList, 1);
        return newList;
    }


    @RequestMapping("/showBookDetailById")
    public String showBookDetailById(String id, Model model) {
        String loginUserId = CookieUtils.getCookie(F.LOGIN_USER_ID_COOKIE_KEY);
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        // 别人的书
        if (!book.getOwner().getId().equals(loginUserId)) {
            return "pages/book/showBorrowBookDetail";
        }
        // 自己的书
        return "pages/book/showBookDetail";
    }

    @RequestMapping("/initAddBook")
    public String initAddBook(Model model) {
        return "pages/addBook";
    }

    @RequestMapping("/addBook")
    public String addBook(@RequestParam("file") CommonsMultipartFile file, @RequestParam("cover") CommonsMultipartFile cover, Book book, Model model)
            throws IllegalStateException, IOException {
        /*System.out.println(file);
        if(file.isEmpty()){
            model.addAttribute("msg","file can not be null");
            return "pages/addBook";
        }*/
        saveFileTo(file, cover, book);
        bookService.addBook(book);
        return "redirect:/user/index.action";
    }

    private void saveFileTo(CommonsMultipartFile file, CommonsMultipartFile cover, Book book) throws IOException {
        String bookUri = null;
        String coverUri = null;
        if (file.getSize() != 0) {

            String filePath = DirUtils.getAbsolutePath(F.UPLOADROOT, file.getOriginalFilename());
            File dest = new File(filePath);
            file.transferTo(dest);
            bookUri = DirUtils.getUri(F.UPLOADROOT, file.getOriginalFilename());
            book.setBookUri(bookUri);

        }
        if (cover.getSize() != 0) {
            String filePath = DirUtils.getAbsolutePath(F.UPLOADROOT, cover.getOriginalFilename());
            File dest = new File(filePath);
            cover.transferTo(dest);
            coverUri = DirUtils.getUri(F.UPLOADROOT, cover.getOriginalFilename());
            book.setCoverUri(coverUri);
        }
    }

    @RequestMapping("/showBookByAuthorId")
    public String showBookByAuthorId(String id, Model model) {
        List<Book> books = bookService.getBookByAuthorId(id);
        model.addAttribute("authorBooks", books);
        return "pages/showAuthorBooks";
    }

    @RequestMapping("/showBookByOwnerId")
    public String showBookByOwnerId(String id, Model model) {
        List<Book> books = bookService.getBookByOwnerId(id);
        model.addAttribute("books", books);
        return "pages/showBooks";
    }

    @RequestMapping("/initModifyBook")
    public String initModifyBook(String id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "pages/modifyBook";
    }

    @RequestMapping("/modifyBook")
    public String modifyBook(@RequestParam("file") CommonsMultipartFile file,
                             @RequestParam("cover") CommonsMultipartFile cover, Book book, Model model) throws IOException {
        saveFileTo(file, cover, book);
        bookService.modifyBook(book);
        return "redirect:/user/index.action";
    }

    @RequestMapping("/removeBook")
    public String removeBook(Book book, Model model) {
        try {
            bookService.removeBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage());
            return "redirect:/book/showBookDetailById.action?id=" + book.getId();
        }
        return "redirect:/user/index.action";
    }

    @RequestMapping("/showAllBooks")
    public String showAllBooks() throws IOException {
        return "pages/showAllBooks";
    }

    // @RequestMapping("/addBook")
    // public String addBook(HttpServletRequest request, Book book, Model model)
    // throws IllegalStateException, IOException {
    // MultipartFile file = null;
    // MultipartResolver multipartResolver = new
    // CommonsMultipartResolver(request.getServletContext());
    // if (multipartResolver.isMultipart(request)) {
    // MultipartHttpServletRequest resolveMultipart =
    // (MultipartHttpServletRequest) request;
    // file = resolveMultipart.getFile("file");
    // if (file != null) {
    // String filePath =
    // RequestResponseBox.getRequest().getServletContext().getRealPath("/files");
    // filePath += "/" + file.getOriginalFilename();
    // File dest = new File(filePath);
    // file.transferTo(dest);
    // }
    // }
    //
    // bookService.addBook(book);
    // return "redirect:/user/index.action";
    // }

}
