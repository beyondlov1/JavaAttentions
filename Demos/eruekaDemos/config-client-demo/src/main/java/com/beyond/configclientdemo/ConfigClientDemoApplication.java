package com.beyond.configclientdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@RefreshScope
public class ConfigClientDemoApplication  {

    @Value("${foo}")
    String foo;

    @RequestMapping("/foo")
    public Object foo(){
        return foo;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientDemoApplication.class, args);
    }

}
