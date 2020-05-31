package com.beyond.traceclient1demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class TraceClient1DemoApplication {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/trace1")
    public Object trace1(){
        logger.info("trace1");
        return restTemplate.getForObject("http://TRACE2/trace2", String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TraceClient1DemoApplication.class, args);
    }

}
