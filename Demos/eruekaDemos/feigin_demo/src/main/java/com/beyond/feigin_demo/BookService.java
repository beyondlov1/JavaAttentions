package com.beyond.feigin_demo;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("BOOK-SERVICE-PROVIDER")
public interface BookService extends com.beyond.bookserviceapi.BookService {

}
