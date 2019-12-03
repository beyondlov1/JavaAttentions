package com.beyond.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableSpringConfigured
public class QuerySwitchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerySwitchDemoApplication.class, args);
    }

}
