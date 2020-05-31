package com.beyond.eureka_client_consumer_demo.service;

import com.beyond.eureka_client_consumer_demo.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.sun.deploy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    RestTemplate restTemplate;


    @HystrixCollapser(batchMethod = "consumeBooksAll",
            scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {
            @HystrixProperty(name="timerDelayInMilliseconds", value = "100")
    })
    public Map consumeBooks(String id){
//        String forObject = restTemplate.getForObject("http://BOOK-SERVICE-PROVIDER/books", String.class);
//        System.out.println(forObject);
//        return forObject;
        return null;
    }

    @SuppressWarnings("unchecked")
    @HystrixCommand(fallbackMethod = "error")
    public List<Map> consumeBooksAll(List<String> ids){
        List<Map> forEntity = restTemplate.getForObject("http://BOOK-SERVICE-PROVIDER/books?ids={1}",
                List.class, StringUtils.join(ids, ","));
        return forEntity;
    }

    public List<Map> error(List<String> ids){
        return new ArrayList<>();
    }
}
