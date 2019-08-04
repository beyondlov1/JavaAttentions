package com.beyond.eureka_client_provider_demo;

import com.beyond.bookserviceapi.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    public List<Book> showBooks(List<String> ids){
        List<Book> list = new ArrayList<>();
        for (String id : ids) {
            list.add(new Book(id,34));
        }

        return list;
    }
}
