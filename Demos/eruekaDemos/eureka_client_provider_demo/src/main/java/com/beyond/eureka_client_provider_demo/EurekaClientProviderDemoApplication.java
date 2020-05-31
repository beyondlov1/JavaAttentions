package com.beyond.eureka_client_provider_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaClientProviderDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProviderDemoApplication.class, args);
    }

}
