package com.example.demo;

import com.example.demo.aop.ApplicationContextHolder;
import com.example.demo.aop.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootTestApplication {

	@Autowired
	private ApplicationContextHolder applicationContextHolder;

	@RequestMapping("/aop")
	public Object execute(){
		Target target = applicationContextHolder.getApplicationContext().getBean("target", Target.class);
		return target.execute();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);
	}
}
