package com.beyond.solrspringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SolrSpringDemoApplication {

    @RequestMapping("/hello")
    public Object hello(){
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(SolrSpringDemoApplication.class, args);
    }

}
