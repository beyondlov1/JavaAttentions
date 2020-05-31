package com.beyond.traceclient2demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class TraceClient2DemoApplication {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/trace2")
    public Object trace2(){
        logger.info("trace2");
        return "from trace2";
    }

    public static void main(String[] args) {
        SpringApplication.run(TraceClient2DemoApplication.class, args);
    }

}
